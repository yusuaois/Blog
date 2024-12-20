package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.AppHttpCodeEnum;
import com.blog.common.ResponseResult;
import com.blog.constants.SystemConstants;
import com.blog.dto.CategoryDto;
import com.blog.entity.Article;
import com.blog.entity.Category;
import com.blog.exception.SystemException;
import com.blog.mapper.CategoryMapper;
import com.blog.service.ArticleService;
import com.blog.service.CategoryService;
import com.blog.utils.BeanCopyUtils;
import com.blog.utils.WordDetectUtils;
import com.blog.vo.CategoryVo;
import com.blog.vo.PageVo;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        // 查询文章表状态为已发布文章
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleWrapper);
        // 获取文章的分类id并去重
        Set<Long> categoryId = articleList.stream().map(c -> c.getCategoryId()).distinct().collect(Collectors.toSet());
        // 根据分类id查询分类信息
        List<Category> categories = listByIds(categoryId);
        categories = categories.stream().filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        // 封装VO返回
        List<CategoryVo> categoryVo = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return ResponseResult.okResult(categoryVo);
    }

    @Override
    public ResponseResult listCategory(Integer pageNum, Integer pageSize, String name, String status) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(name), Category::getName, name);
        queryWrapper.eq(StringUtils.hasText(status), Category::getStatus, status);
        Page<Category> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        PageVo vo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult addCategory(CategoryDto dto) {
        // 校验是否有文字
        if (!StringUtils.hasText(dto.getName())) {
            throw new SystemException(AppHttpCodeEnum.CATEGORY_NAME_NOT_NULL);
        }
        // 敏感词检测
        WordDetectUtils.checkSensitiveWord(dto.getName());
        // 名称已存在
        if (count(new LambdaQueryWrapper<Category>().eq(Category::getName, dto.getName())) > 0) {
            throw new SystemException(AppHttpCodeEnum.CATEGORY_NAME_EXIST);
        }
        Category category = BeanCopyUtils.copyBean(dto, Category.class);
        save(category);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult selectCategoryById(Long id) {
        Category category = getById(id);
        CategoryVo categoryVo = BeanCopyUtils.copyBean(category, CategoryVo.class);
        return ResponseResult.okResult(categoryVo);
    }

    @Override
    public ResponseResult updateCategory(CategoryDto dto) {
        if (!StringUtils.hasText(dto.getName())) {
            throw new SystemException(AppHttpCodeEnum.CATEGORY_NAME_NOT_NULL);
        }
        // 敏感词检测
        WordDetectUtils.checkSensitiveWord(dto.getName());
        // 名称已存在
        if (count(new LambdaQueryWrapper<Category>().eq(Category::getName, dto.getName())) > 0) {
            throw new SystemException(AppHttpCodeEnum.CATEGORY_NAME_EXIST);
        }
        Category category = BeanCopyUtils.copyBean(dto, Category.class);
        updateById(category);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteCategory(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }
}
