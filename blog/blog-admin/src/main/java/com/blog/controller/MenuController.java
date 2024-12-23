package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.common.ResponseResult;
import com.blog.entity.SysMenu;
import com.blog.service.MenuService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@Api(tags = "菜单管理")
@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    @Operation(summary = "获取所有菜单")
    public ResponseResult listAllMenu(String menuName, String status) {
        return menuService.listAllMenu(menuName, status);
    }

    @PostMapping()
    @Operation(summary = "添加菜单")
    public ResponseResult<SysMenu> addNewMenu(@RequestBody SysMenu menu) {
        return menuService.addNewMenu(menu);
    }

    @GetMapping("/{id}")
    @Operation(summary = "更新菜单-根据ID获取菜单")
    public ResponseResult selectMenuById(@PathVariable("id") Long id) {
        return menuService.selectMenuById(id);
    }

    @PutMapping()
    @Operation(summary = "更新菜单-更新菜单信息")
    public ResponseResult updateMenuById(@RequestBody SysMenu menu) {
        return menuService.updateMenuById(menu);
    }

    @DeleteMapping("/{menuId}")
    @Operation(summary = "删除菜单-根据ID删除菜单")
    public ResponseResult deleteMenuById(@PathVariable("menuId") Long menuId) {
        return menuService.deleteMenuById(menuId);
    }

    @GetMapping("/treeselect")
    @Operation(summary = "获取菜单-菜单树")
    public ResponseResult treeSelect() {
        return menuService.treeSelect();
    }

    @GetMapping("/roleMenuTreeselect/{id}")
    @Operation(summary = "获取菜单-角色菜单树")
    public ResponseResult roleMenuTreeselect(@PathVariable Long id) {
        return menuService.roleMenuTreeselect(id);
    }
    
}
