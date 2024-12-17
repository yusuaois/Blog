package com.blog.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.blog.common.ResponseResult;
import com.blog.dto.AddArticleDto;
import com.blog.service.ArticleService;
import com.blog.service.MenuService;

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
@RestController
@RequestMapping("/content/article")
public class ArticleController {
    
    @Autowired
    private ArticleService articleService;

    @Autowired
    private MenuService menuService;

    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto article){
        return articleService.add(article);
    }

    @GetMapping("/list")
    public ResponseResult selectArticleList(Integer pageNum,Integer pageSize,String title,String summary) {
        return articleService.selectArticleList(pageNum,pageSize,title,summary);
    }
    
    @GetMapping("/{id}")
    public ResponseResult selectArticleById(@PathVariable("id") Long id){
        return articleService.selectArticleById(id);
    }

    @PutMapping()
    public ResponseResult updateArticleById(@RequestBody AddArticleDto articleDto) {
        return articleService.updateArticleById(articleDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteArticleById(@PathVariable("id") Long id) {
        return articleService.deleteArticleById(id);
    }


}
