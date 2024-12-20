package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.AppHttpCodeEnum;
import com.blog.common.ResponseResult;
import com.blog.constants.SystemConstants;
import com.blog.dto.LinkDto;
import com.blog.entity.Link;
import com.blog.exception.SystemException;
import com.blog.mapper.LinkMapper;
import com.blog.service.LinkService;
import com.blog.utils.BeanCopyUtils;
import com.blog.utils.WordDetectUtils;
import com.blog.vo.LinksVo;
import com.blog.vo.PageVo;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 友链 服务实现类
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {
    @Override
    public ResponseResult getAllLink() {
        // 查询所有审核通过的友联
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);
        // 封装成Vo
        List<LinksVo> linksVo = BeanCopyUtils.copyBeanList(links, LinksVo.class);
        return ResponseResult.okResult(linksVo);
    }

    @Override
    public ResponseResult listLink(Integer pageNum, Integer pageSize, String name, String status) {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(name), Link::getName, name);
        queryWrapper.eq(StringUtils.hasText(status), Link::getStatus, status);
        Page<Link> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addLink(LinkDto dto) {
        if (!StringUtils.hasText(dto.getName()))
            throw new SystemException(AppHttpCodeEnum.LINK_NAME_NOT_NULL);
        // 敏感词检测
        WordDetectUtils.checkSensitiveWord(dto.getName());
        WordDetectUtils.checkSensitiveWord(dto.getDescription());

        if (count(new LambdaQueryWrapper<Link>().eq(Link::getName, dto.getName())) > 0)
            throw new SystemException(AppHttpCodeEnum.LINK_NAME_EXIST);
        Link link = BeanCopyUtils.copyBean(dto, Link.class);
        save(link);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult selectLinkById(@PathVariable Long id) {
        Link link = getById(id);
        LinksVo linksVo = BeanCopyUtils.copyBean(link, LinksVo.class);
        return ResponseResult.okResult(linksVo);
    }

    @Override
    public ResponseResult updateLink(LinkDto dto) {
        if (!StringUtils.hasText(dto.getName()))
            throw new SystemException(AppHttpCodeEnum.LINK_NAME_NOT_NULL);
        // 敏感词检测
        WordDetectUtils.checkSensitiveWord(dto.getName());
        WordDetectUtils.checkSensitiveWord(dto.getDescription());
        if (count(new LambdaQueryWrapper<Link>().eq(Link::getName, dto.getName())) > 0)
            throw new SystemException(AppHttpCodeEnum.LINK_NAME_EXIST);
        Link link = BeanCopyUtils.copyBean(dto, Link.class);
        updateById(link);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteLink(@PathVariable Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }
}
