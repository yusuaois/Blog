package com.blog.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.annotation.SystemLog;
import com.blog.common.ResponseResult;
import com.blog.dto.AddUserDto;
import com.blog.service.UserService;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    @SystemLog(businessName = "后台获取用户列表")
    public ResponseResult userList(Integer pageNum, Integer pageSize, String userName, String phonenumber,
            String status) {
        return userService.userList(pageNum, pageSize, userName, phonenumber, status);
    }

    @RequestMapping()
    public ResponseResult addUser(@RequestBody AddUserDto user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
    
    
    
}
