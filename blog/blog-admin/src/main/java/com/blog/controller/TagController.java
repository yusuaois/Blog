package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.common.AppHttpCodeEnum;
import com.blog.common.ResponseResult;
import com.blog.dto.TagListDto;
import com.blog.entity.Tag;
import com.blog.exception.SystemException;
import com.blog.service.TagService;
import com.blog.vo.PageVo;
import com.blog.vo.TagInfoVo;

import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>
 * 标签 前端控制器
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        return tagService.pageTagList(pageNum, pageSize, tagListDto);
    }

    @PostMapping
    public ResponseResult addTag(@RequestBody Tag tag) {
        if (!StringUtils.hasText(tag.getName()))
            throw new SystemException(AppHttpCodeEnum.TAG_NAME_NOT_NULL);
        return tagService.addTag(tag);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteTag(@PathVariable Long id) {
        return tagService.deleteTag(id);
    }

    @GetMapping("/{id}")
    public ResponseResult getTagInfo(@PathVariable Long id) {
        return tagService.getTagInfo(id);
    }

    @PutMapping
    public ResponseResult updateTag(@RequestBody Tag tag) {
        return tagService.updateTag(tag);
    }

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag() {
        return tagService.listAllTag();
    }

}
