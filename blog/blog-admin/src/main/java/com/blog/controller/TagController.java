package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

import com.blog.common.AppHttpCodeEnum;
import com.blog.common.ResponseResult;
import com.blog.dto.TagListDto;
import com.blog.entity.SysTag;
import com.blog.exception.SystemException;
import com.blog.service.TagService;
import com.blog.vo.PageVo;

/**
 * <p>
 * 标签 前端控制器
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Api(tags = "文章模块")
@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    @Operation(summary = "分页查询标签列表")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        return tagService.pageTagList(pageNum, pageSize, tagListDto);
    }

    @PostMapping
    @Operation(summary = "新增标签")
    public ResponseResult addTag(@RequestBody SysTag tag) {
        if (!StringUtils.hasText(tag.getName()))
            throw new SystemException(AppHttpCodeEnum.TAG_NAME_NOT_NULL);
        return tagService.addTag(tag);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除标签")
    public ResponseResult deleteTag(@PathVariable Long id) {
        return tagService.deleteTag(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "更新标签-获取标签信息")
    public ResponseResult getTagInfo(@PathVariable Long id) {
        return tagService.getTagInfo(id);
    }

    @PutMapping
    @Operation(summary = "更新标签-提交标签信息")
    public ResponseResult updateTag(@RequestBody SysTag tag) {
        return tagService.updateTag(tag);
    }

    @GetMapping("/listAllTag")
    @Operation(summary = "查询所有标签")
    public ResponseResult listAllTag() {
        return tagService.listAllTag();
    }

}
