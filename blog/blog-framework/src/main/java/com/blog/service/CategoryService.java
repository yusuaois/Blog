package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.common.ResponseResult;
import com.blog.dto.CategoryDto;
import com.blog.entity.Category;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();

    ResponseResult listCategory(Integer pageNum, Integer pageSize, String name, String status);

    ResponseResult addCategory(CategoryDto dto);

    ResponseResult selectCategoryById(Long id);

    ResponseResult updateCategory(CategoryDto dto);

    ResponseResult deleteCategory(Long id);

}
