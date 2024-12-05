package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.Link;
import com.blog.mapper.LinkMapper;
import com.blog.service.ILinkService;

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
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements ILinkService {

}
