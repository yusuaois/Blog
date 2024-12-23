package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.annotation.SystemLog;
import com.blog.common.ResponseResult;
import com.blog.service.LinkService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

/**
 * <p>
 * 友链 前端控制器
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Api(tags = "友链管理")
@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    LinkService linkService;

    @GetMapping("/getAllLink")
    @SystemLog(businessName = "获取所有友链")
    @Operation(summary = "获取所有友链")
    public ResponseResult getAllLink() {
        return linkService.getAllLink();
    };
}
