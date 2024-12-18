package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.common.ResponseResult;
import com.blog.dto.LinkDto;
import com.blog.service.LinkService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * <p>
 * 友链 前端控制器
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@RestController
@RequestMapping("/content/link")
public class LinkController {

    @Autowired LinkService linkService;

    @GetMapping("/list")
    public ResponseResult listLink(Integer pageNum, Integer pageSize, String name, String status) {
        return linkService.listLink(pageNum, pageSize, name, status);
    }

    @PostMapping()
    public ResponseResult addLink(@RequestBody LinkDto dto) {
        return linkService.addLink(dto);
    }

    @GetMapping("/{id}")
    public ResponseResult selectLinkById(@PathVariable Long id) {
        return linkService.selectLinkById(id);
    }

    @PutMapping()
    public ResponseResult updateLink(@RequestBody LinkDto dto) {
        return linkService.updateLink(dto);
    }
    
    
}
