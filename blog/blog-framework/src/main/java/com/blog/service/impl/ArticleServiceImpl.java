package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.ResponseResult;
import com.blog.constants.SystemConstants;
import com.blog.entity.Article;
import com.blog.entity.ArticleTag;
import com.blog.entity.Category;
import com.blog.mapper.ArticleMapper;
import com.blog.service.ArticleService;
import com.blog.service.ArticleTagService;
import com.blog.service.CategoryService;
import com.blog.utils.BeanCopyUtils;
import com.blog.utils.WordDetectUtils;
import com.blog.utils.redis.RedisCache;
import com.blog.vo.ArticleDetailVo;
import com.blog.vo.ArticleListVo;
import com.blog.vo.HotArticleVo;
import com.blog.vo.PageVo;
import com.blog.dto.AddArticleDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Lazy
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleTagService articleTagService;

    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);

        queryWrapper.orderByDesc(Article::getViewCount);

        Page<Article> page = new Page(1, 10);
        page(page, queryWrapper);

        List<Article> result = page.getRecords();

        result.stream()
                .peek(article -> {
                    Integer viewCount = (Integer) redisCache.getCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT,
                            article.getId().toString());
                    if (viewCount != null) {
                        article.setViewCount(viewCount.longValue());
                    }
                })
                .collect(Collectors.toList());

        List<HotArticleVo> vs = BeanCopyUtils.copyBeanList(result, HotArticleVo.class);

        return ResponseResult.okResult(vs);
    }// 热门文章列表

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        // 查询条件
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 如果有分类id，则添加分类条件
        queryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);
        // 正式发布
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 对isTop进行降序
        queryWrapper.orderByDesc(Article::getIsTop);
        // 分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);

        // 查询分类名称（名称过长处理）
        List<Article> articles = page.getRecords();

        // articleId查询articleName进行设置
        articles.stream()
                .peek(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .peek(article -> {
                    Integer viewCount = (Integer) redisCache.getCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT,
                            article.getId().toString());
                    if (viewCount != null) {
                        article.setViewCount(viewCount.longValue());
                    }
                })

                .collect(Collectors.toList());

        // 封装查询结果
        List<ArticleListVo> articleVo = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);

        // 封装分页对象
        PageVo pageVo = new PageVo(articleVo, page.getTotal());

        return ResponseResult.okResult(pageVo);

    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        // 根据id查询文章
        Article article = getById(id);
        // 从redis中获取ViewCount
        Integer viewCount = redisCache.getCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT, id.toString());
        article.setViewCount(viewCount.longValue());
        // 转化成Vo
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        // 查询对应的分类名称
        Category category = categoryService.getById(articleDetailVo.getCategoryId());
        // 根据分类id查询分类名称
        if (category != null)
            articleDetailVo.setCategoryName(category.getName());
        // 封装相应返回
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        redisCache.incrementCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT, id.toString(), 1);
        return ResponseResult.okResult();
    }

    @Override
    @Transactional
    public ResponseResult add(AddArticleDto articleDto) {
        // 检测内容空白
        if (!StringUtils.hasText(articleDto.getContent())) {
            throw new RuntimeException("内容不能为空");
        }
        if (!StringUtils.hasText(articleDto.getTitle())) {
            throw new RuntimeException("标题不能为空");
        }
        if (!StringUtils.hasText(articleDto.getSummary())) {
            throw new RuntimeException("摘要不能为空");
        }

        // 敏感词检测
        WordDetectUtils.checkSensitiveWord(articleDto.getTitle());
        WordDetectUtils.checkSensitiveWord(articleDto.getContent());
        WordDetectUtils.checkSensitiveWord(articleDto.getSummary());

        // 添加 博客
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        save(article);

        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());

        // 添加 博客和标签的关联
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult selectArticleList(Integer pageNum, Integer pageSize, String title, String summary) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 根据标题和摘要进行模糊查询
        queryWrapper.like(StringUtils.hasText(title), Article::getTitle, title);
        queryWrapper.like(StringUtils.hasText(summary), Article::getSummary, summary);
        // 分页查询
        Page page = new Page(pageNum, pageSize);
        page(page, queryWrapper);
        List<Article> records = page.getRecords();
        PageVo pageVo = new PageVo(records, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult selectArticleById(Long id) {
        Article article = getById(id);
        return ResponseResult.okResult(article);
    }

    @Override
    public ResponseResult updateArticleById(AddArticleDto articleDto) {
        // 检测内容空白
        if (!StringUtils.hasText(articleDto.getContent())) {
            throw new RuntimeException("内容不能为空");
        }
        if (!StringUtils.hasText(articleDto.getTitle())) {
            throw new RuntimeException("标题不能为空");
        }
        if (!StringUtils.hasText(articleDto.getSummary())) {
            throw new RuntimeException("摘要不能为空");
        }

        // 敏感词检测
        WordDetectUtils.checkSensitiveWord(articleDto.getTitle());
        WordDetectUtils.checkSensitiveWord(articleDto.getContent());
        WordDetectUtils.checkSensitiveWord(articleDto.getSummary());

        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getId, article.getId());
        update(article, queryWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteArticleById(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }
}
