package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.ResponseResult;
import com.blog.constants.SystemConstants;
import com.blog.entity.Link;
import com.blog.mapper.LinkMapper;
import com.blog.service.LinkService;
import com.blog.utils.BeanCopyUtils;
import com.blog.vo.LinksVo;

import java.util.List;

import org.springframework.stereotype.Service;

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
        //查询所有审核通过的友联
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus,SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);
        //封装成Vo
        List<LinksVo> linksVo =  BeanCopyUtils.copyBeanList(links,LinksVo.class);
        return ResponseResult.okResult(linksVo);
    }
}
