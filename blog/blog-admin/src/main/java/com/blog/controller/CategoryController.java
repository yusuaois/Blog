package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.service.CategoryService;
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

@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Autowired CategoryService categoryService;

    @RequestMapping("/listAllCategory")
    @SystemLog(businessName = "获取分类列表")
    public ResponseResult listAllCategory(){
        return categoryService.getCategoryList();
    }

    
    

}
