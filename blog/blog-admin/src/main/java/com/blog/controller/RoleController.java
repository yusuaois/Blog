package com.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.common.ResponseResult;
import com.blog.dto.RoleDto;
import com.blog.entity.SysRole;
import com.blog.service.RoleService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
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
@Api(tags = "角色信息")
@RestController
@RequestMapping("/system/role")
public class RoleController {
    
    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    @Operation(summary = "前台-分页查询角色列表")
    public ResponseResult listRole(Integer pageNum, Integer pageSize, String roleName, String status) {
        return roleService.listRole(pageNum, pageSize, roleName, status,null);
    }

    @PutMapping("/changeStatus")
    @Operation(summary = "修改角色状态")
    public ResponseResult changeStatus(@RequestBody SysRole role) {
        return roleService.changeStatus(role);
    }

    @PostMapping()
    @Operation(summary = "新增角色")
    public ResponseResult addRole(@RequestBody RoleDto role) {
        return roleService.addRole(role);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "更新角色-根据id查询角色")
    public ResponseResult roleInfo(@PathVariable Long id) {
        return roleService.roleInfo(id);
    }
    
    @PutMapping()
    @Operation(summary = "更新角色-更新角色信息")
    public ResponseResult updateRole(@RequestBody RoleDto role) {
        return roleService.updateRole(role);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除角色")
    public ResponseResult deleteRole(@PathVariable Long id) {
        return roleService.deleteRole(id);
    }

    @GetMapping("/listAllRole")
    @Operation(summary = "后台-查询所有角色")
    public ResponseResult listAllRole() {
        return roleService.listAllRole();
    }

}
