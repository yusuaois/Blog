package com.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.common.ResponseResult;
import com.blog.dto.AddRoleDto;
import com.blog.entity.SysRole;
import com.blog.service.RoleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseResult addRole(@RequestBody AddRoleDto role) {
        return roleService.addRole(role);
    }
    
    
    

}
