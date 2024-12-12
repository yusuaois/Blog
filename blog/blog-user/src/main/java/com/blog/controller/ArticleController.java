package com.blog.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.blog.annotation.SystemLog;
import com.blog.common.ResponseResult;
import com.blog.service.ArticleService;


/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/hotArticleList")
    @SystemLog(businessName = "获取热门文章列表")
    public ResponseResult hotArticleList(){
        
        ResponseResult result =  articleService.hotArticleList();
        return result;
    }

    @RequestMapping("/articleList")
    @SystemLog(businessName = "获取文章列表")
    public ResponseResult articleList(Integer pageNum, Integer pageSize,Long categoryId){
        return  articleService.articleList(pageNum, pageSize, categoryId);
    }

    @RequestMapping("/{id}")
    @SystemLog(businessName = "获取文章详情")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }

    @PutMapping("/updateViewCount/{id}")
    @SystemLog(businessName = "更新文章浏览量")
    public ResponseResult updateViewCount(@PathVariable("id") Long id){
        return articleService.updateViewCount(id);
    }

}
