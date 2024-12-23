package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

import com.blog.annotation.SystemLog;
import com.blog.common.ResponseResult;

/**
 * <p>
 * 分类表 前端控制器
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Api(tags = "分类管理")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/getCategoryList")
    @SystemLog(businessName = "获取分类列表")
    @Operation(summary = "获取分类列表")
    public ResponseResult getCategoryList() {
        return categoryService.getCategoryList();
    }

}
