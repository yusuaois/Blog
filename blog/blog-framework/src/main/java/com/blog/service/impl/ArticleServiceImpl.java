package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.ResponseResult;
import com.blog.constants.SystemConstants;
import com.blog.entity.Article;
import com.blog.entity.Category;
import com.blog.mapper.ArticleMapper;
import com.blog.service.IArticleService;
import com.blog.service.ICategoryService;
import com.blog.utils.BeanCopyUtils;
import com.blog.vo.ArticleDetailVo;
import com.blog.vo.ArticleListVo;
import com.blog.vo.HotArticleVo;
import com.blog.vo.PageVo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
    @Lazy
    @Autowired
    private ICategoryService categoryService;

    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);

        queryWrapper.orderByDesc(Article::getViewCount);

        Page<Article> page = new Page(1, 10);
        page(page, queryWrapper);

        List<Article> result = page.getRecords();

        // bean拷贝 将实体类转换成vo类：赋值
        // List<hotArticleVo> articleVos = new ArrayList<>();
        // for(Article article:result){
        // hotArticleVo Vo = new hotArticleVo();
        // BeanUtils.copyProperties(article,Vo);
        // articleVos.add(Vo);
        // }
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
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());

        // for (Article article : articles) {
        // Category category = categoryService.getById(article.getCategoryId());
        // article.setCategoryName(category.getName());
        // }

        // 封装查询结果
        List<ArticleListVo> articleVo = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);
        // 封装分页对象
        PageVo pageVo = new PageVo(articleVo, page.getTotal());

        return ResponseResult.okResult(pageVo);

    }

    @Override
    public ResponseResult getArticleDetail(Long id){
        // 根据id查询文章
        Article article = getById(id);
        //转化成Vo
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article,ArticleDetailVo.class);
        //查询对应的分类名称
        Category category = categoryService.getById(articleDetailVo.getCategoryId()) ;
        //根据分类id查询分类名称
        if(category != null)articleDetailVo.setCategoryName(category.getName());
        //封装相应返回
        return ResponseResult.okResult(articleDetailVo);
    }
}
