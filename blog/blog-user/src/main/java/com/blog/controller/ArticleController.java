package com.blog.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.blog.common.ResponseResult;
import com.blog.service.IArticleService;


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
    private IArticleService ArticleService;

    @RequestMapping("/hotArticleList")
    public ResponseResult hotArticleList(){
        
        ResponseResult result =  ArticleService.hotArticleList();
        return result;
    }

    @RequestMapping("/articleList")
    public ResponseResult articleList(Integer pageNum, Integer pageSize,Long categoryId){
        return  ArticleService.articleList(pageNum, pageSize, categoryId);
    }

    @RequestMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        return ArticleService.getArticleDetail(id);
    }

}
