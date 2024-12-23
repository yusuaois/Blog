package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.common.ResponseResult;
import com.blog.dto.TagListDto;
import com.blog.entity.SysTag;
import com.blog.vo.TagInfoVo;

/**
 * <p>
 * 标签 服务类
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
public interface TagService extends IService<SysTag> {

    ResponseResult pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult addTag(SysTag tag);

    ResponseResult deleteTag(Long id);

    ResponseResult getTagInfo(Long id);

    ResponseResult updateTag(SysTag tag);

    ResponseResult getTagList();

    ResponseResult listAllTag();

}
