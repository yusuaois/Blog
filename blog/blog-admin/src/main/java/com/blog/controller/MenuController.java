package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.common.ResponseResult;
import com.blog.entity.SysMenu;
import com.blog.service.MenuService;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/list")
    public ResponseResult listAllMenu(String menuName, String status) {
        return menuService.listAllMenu(menuName, status);
    }

    @RequestMapping()
    public ResponseResult<SysMenu> addNewMenu(@RequestBody SysMenu menu) {
        return menuService.addNewMenu(menu);
    }

    @GetMapping("/{id}")
    public ResponseResult selectMenuById(@PathVariable("id") Long id) {
        return menuService.selectMenuById(id);
    }

    @PutMapping()
    public ResponseResult updateMenuById(@RequestBody SysMenu menu) {
        return menuService.updateMenuById(menu);
    }

    @DeleteMapping("/{menuId}")
    public ResponseResult deleteMenuById(@PathVariable("menuId") Long menuId) {
        return menuService.deleteMenuById(menuId);
    }

    @GetMapping("/treeselect")
    public ResponseResult treeSelect() {
        return menuService.treeSelect();
    }

}
