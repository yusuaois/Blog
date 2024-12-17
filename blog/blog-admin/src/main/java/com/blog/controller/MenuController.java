package com.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.common.ResponseResult;
import com.blog.service.MenuService;

@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/list")
    public ResponseResult listAllMenu(String menuName,String status){
        return menuService.listAllMenu( menuName, status);
    }
}
