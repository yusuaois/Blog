package com.blog.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.entity.SysRole;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
public interface RoleService extends IService<SysRole> {

    List<String> selectRoleKeyByUserId(Long id);

}
