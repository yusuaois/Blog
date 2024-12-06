package com.blog.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.blog.common.ResponseResult;

import com.blog.entity.User;
import com.blog.service.BlogLoginService;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    @RequestMapping("/login")
    public ResponseResult login(@RequestBody User User){
        return blogLoginService.login(User);
    }

    
    
}
