package com.blog.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.blog.annotation.SystemLog;
import com.blog.common.ResponseResult;
import com.blog.dto.AddArticleDto;
import com.blog.service.ArticleService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Api(tags = "文章模块")
@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/articleList")
    @SystemLog(businessName = "获取文章列表")
    @Operation(summary = "分页查询文章列表")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.articleList(pageNum, pageSize, categoryId);
    }

    @PostMapping
    @Operation(summary = "添加文章")
    public ResponseResult add(@RequestBody AddArticleDto article) {
        return articleService.add(article);
    }

    @GetMapping("/list")
    @Operation(summary = "查询文章列表")
    public ResponseResult selectArticleList(Integer pageNum, Integer pageSize, String title, String summary) {
        return articleService.selectArticleList(pageNum, pageSize, title, summary);
    }

    @GetMapping("/{id}")
    @Operation(summary = "更新文章-根据id查询文章")
    public ResponseResult selectArticleById(@PathVariable("id") Long id) {
        return articleService.selectArticleById(id);
    }

    @PutMapping()
    @Operation(summary = "更新文章-更新文章信息")
    public ResponseResult updateArticleById(@RequestBody AddArticleDto articleDto) {
        return articleService.updateArticleById(articleDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除文章-根据id删除文章")
    public ResponseResult deleteArticleById(@PathVariable("id") Long id) {
        return articleService.deleteArticleById(id);
    }

}
