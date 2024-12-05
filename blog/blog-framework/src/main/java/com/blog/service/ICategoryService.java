package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.common.ResponseResult;
import com.blog.entity.Category;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
public interface ICategoryService extends IService<Category> {

    ResponseResult getCategoryList();

}
