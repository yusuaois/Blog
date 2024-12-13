package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.ResponseResult;
import com.blog.dto.TagListDto;
import com.blog.entity.Tag;
import com.blog.mapper.TagMapper;
import com.blog.service.TagService;
import com.blog.utils.SecurityUtils;
import com.blog.vo.PageVo;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 标签 服务实现类
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Override
    public ResponseResult pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        // 分页查询
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(tagListDto.getName()), Tag::getName, tagListDto.getName());
        queryWrapper.like(StringUtils.hasText(tagListDto.getRemark()), Tag::getRemark, tagListDto.getRemark());

        Page<Tag> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        // 封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addTag(Tag tag) {
        Long userId = SecurityUtils.getUserId();
        tag.setCreateBy(userId);
        save(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteTag(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }


}
