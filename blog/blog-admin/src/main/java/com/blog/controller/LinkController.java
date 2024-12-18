package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.annotation.SystemLog;
import com.blog.common.ResponseResult;
import com.blog.service.LinkService;

/**
 * <p>
 * 友链 前端控制器
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired LinkService linkService;

    // @GetMapping("/list")
    // public ResponseResult listLinks(Integer pageNum, Integer pageSize, String name, String status) {
    //     return categoryService.listCategory(pageNum, pageSize, name, status);
    // }
}
