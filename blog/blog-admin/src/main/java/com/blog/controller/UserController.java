package com.blog.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import com.blog.annotation.SystemLog;
import com.blog.common.ResponseResult;
import com.blog.dto.UserDto;
import com.blog.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    @SystemLog(businessName = "后台获取用户列表")
    @Operation(summary = "分页查询用户列表")
    public ResponseResult userList(Integer pageNum, Integer pageSize, String userName, String phonenumber,
            String status) {
        return userService.userList(pageNum, pageSize, userName, phonenumber, status);
    }

    @PostMapping()
    @SystemLog(businessName = "添加用户")
    @Operation(summary = "添加用户")
    public ResponseResult addUser(@RequestBody UserDto user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    @SystemLog(businessName = "删除用户")
    @Operation(summary = "删除用户")
    public ResponseResult deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    @SystemLog(businessName = "更新用户-查询用户信息")
    @Operation(summary = "更新用户-查询用户信息")
    public ResponseResult userDetail(@PathVariable Long id) {
        return userService.userDetail(id);
    }

    @PutMapping()
    @SystemLog(businessName = "更新用户-更新用户信息")
    @Operation(summary = "更新用户-更新用户信息")
    public ResponseResult updateUser(@RequestBody UserDto user) {
        return userService.updateUser(user);
    }

}
