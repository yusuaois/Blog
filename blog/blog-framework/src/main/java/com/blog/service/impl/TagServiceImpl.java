package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.AppHttpCodeEnum;
import com.blog.common.ResponseResult;
import com.blog.constants.SystemConstants;
import com.blog.dto.TagListDto;
import com.blog.entity.SysTag;
import com.blog.exception.SystemException;
import com.blog.mapper.TagMapper;
import com.blog.service.TagService;
import com.blog.utils.BeanCopyUtils;
import com.blog.utils.SecurityUtils;
import com.blog.utils.WordDetectUtils;
import com.blog.vo.PageVo;
import com.blog.vo.TagInfoVo;

import java.util.List;

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
public class TagServiceImpl extends ServiceImpl<TagMapper, SysTag> implements TagService {
    @Override
    public ResponseResult pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        // 分页查询
        LambdaQueryWrapper<SysTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(tagListDto.getName()), SysTag::getName, tagListDto.getName());
        queryWrapper.like(StringUtils.hasText(tagListDto.getRemark()), SysTag::getRemark, tagListDto.getRemark());

        Page<SysTag> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        // 封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addTag(SysTag tag) {
        // 为空
        if (!StringUtils.hasText(tag.getName()))
            throw new SystemException(AppHttpCodeEnum.INPUT_FORMAT_ERROR);
        // 敏感词
        WordDetectUtils.checkSensitiveWord(tag.getName());
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

    @Override
    public ResponseResult getTagInfo(Long id) {
        SysTag tag = getById(id);
        TagInfoVo tagInfoVo = BeanCopyUtils.copyBean(tag, TagInfoVo.class);
        return ResponseResult.okResult(tagInfoVo);
    }

    @Override
    public ResponseResult updateTag(SysTag tag) {
        // 为空
        if (!StringUtils.hasText(tag.getName()))
            throw new SystemException(AppHttpCodeEnum.INPUT_FORMAT_ERROR);
        // 敏感词
        WordDetectUtils.checkSensitiveWord(tag.getName());
        updateById(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getTagList() {
        List<SysTag> tags = list();
        List<TagInfoVo> tagListVos = BeanCopyUtils.copyBeanList(tags, TagInfoVo.class);
        return ResponseResult.okResult(tagListVos);
    }

    @Override
    public ResponseResult listAllTag() {
        // 筛选状态正常标签
        LambdaQueryWrapper<SysTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysTag::getDelFlag, SystemConstants.TAG_STATUS_NORMAL);

        List<SysTag> tags = list(queryWrapper);
        List<TagInfoVo> tagListVos = BeanCopyUtils.copyBeanList(tags, TagInfoVo.class);
        return ResponseResult.okResult(tagListVos);
    }
}
