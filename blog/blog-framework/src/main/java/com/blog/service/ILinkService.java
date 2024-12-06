package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.common.ResponseResult;
import com.blog.entity.Link;

/**
 * <p>
 * 友链 服务类
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
public interface ILinkService extends IService<Link> {

    public ResponseResult getAllLink();

}
