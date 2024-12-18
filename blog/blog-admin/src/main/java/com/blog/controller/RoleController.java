package com.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.common.ResponseResult;
import com.blog.dto.RoleDto;
import com.blog.entity.SysRole;
import com.blog.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {
    
    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public ResponseResult listRole(Integer pageNum, Integer pageSize, String roleName, String status) {
        return roleService.listRole(pageNum, pageSize, roleName, status);
    }

    @RequestMapping("/changeStatus")//前端的格式为{roleId: "11", status: "0"},后端的格式为{id: 11, status: "0"}
    public ResponseResult changeStatus(@RequestBody SysRole role) {
        return roleService.changeStatus(role);
    }

    @PostMapping()
    public ResponseResult addRole(@RequestBody RoleDto role) {
        return roleService.addRole(role);
    }
    
    @GetMapping("/{id}")
    public ResponseResult roleInfo(@PathVariable Long id) {
        return roleService.roleInfo(id);
    }
    
    @PutMapping()
    public ResponseResult updateRole(@RequestBody RoleDto role) {
        return roleService.updateRole(role);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteRole(@PathVariable Long id) {
        return roleService.deleteRole(id);
    }

}
