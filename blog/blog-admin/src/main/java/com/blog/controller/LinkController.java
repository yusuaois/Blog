package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.common.ResponseResult;
import com.blog.dto.LinkDto;
import com.blog.service.LinkService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



/**
 * <p>
 * 友链 前端控制器
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Api(tags = "友链模块") 
@RestController
@RequestMapping("/content/link")
public class LinkController {

    @Autowired LinkService linkService;

    @GetMapping("/list")
    @Operation(summary = "获取友链-分页查询")
    public ResponseResult listLink(Integer pageNum, Integer pageSize, String name, String status) {
        return linkService.listLink(pageNum, pageSize, name, status);
    }

    @PostMapping()
    @Operation(summary = "新增友链")
    public ResponseResult addLink(@RequestBody LinkDto dto) {
        return linkService.addLink(dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "更新友链-根据ID查询友链")
    public ResponseResult selectLinkById(@PathVariable Long id) {
        return linkService.selectLinkById(id);
    }

    @PutMapping()
    @Operation(summary = "更新友链-更新友链信息")
    public ResponseResult updateLink(@RequestBody LinkDto dto) {
        return linkService.updateLink(dto);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "删除友链-根据ID删除友链")
    public ResponseResult deleteLink(@PathVariable Long id) {
        return linkService.deleteLink(id);
    }
}
