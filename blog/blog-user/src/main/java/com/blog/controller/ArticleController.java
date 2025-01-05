package com.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.blog.annotation.SystemLog;
import com.blog.common.ResponseResult;
import com.blog.service.ArticleService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Api(tags = "文章管理")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    @SystemLog(businessName = "获取热门文章列表")
    @Operation(summary = "获取热门文章列表")
    public ResponseResult hotArticleList() {
        ResponseResult result = articleService.hotArticleList();
        return result;
    }

    @GetMapping("/articleList")
    @SystemLog(businessName = "获取文章列表")
    @Operation(summary = "分页查询文章列表")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.articleList(pageNum, pageSize, categoryId);
    }

    @GetMapping("/{id}")
    @SystemLog(businessName = "获取文章详情")
    @Operation(summary = "根据id查询文章详情")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }

    @PutMapping("/updateViewCount/{id}")
    @SystemLog(businessName = "增加文章浏览量")
    @Operation(summary = "增加文章浏览量")
    public ResponseResult updateViewCount(@PathVariable("id") Long id) {
        return articleService.updateViewCount(id);
    }

    @GetMapping("/updateLikeCount/{id}")
    public ResponseResult updateLikeCount(@PathVariable("id") Long id) {
        return articleService.updateLikeCount(id);
    }

}
