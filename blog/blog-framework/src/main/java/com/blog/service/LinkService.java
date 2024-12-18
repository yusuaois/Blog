package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.common.ResponseResult;
import com.blog.dto.LinkDto;
import com.blog.entity.Link;

/**
 * <p>
 * 友链 服务类
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
public interface LinkService extends IService<Link> {

    public ResponseResult getAllLink();

    public ResponseResult listLink(Integer pageNum, Integer pageSize, String name, String status);

    public ResponseResult addLink(LinkDto dto);

    public ResponseResult selectLinkById(Long id);

    public ResponseResult updateLink(LinkDto dto);

    public ResponseResult deleteLink(Long id);

}
