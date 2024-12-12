package com.blog.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.entity.SysMenu;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
public interface MenuService extends IService<SysMenu> {

    List<String> selectPermsByUserId(Long id);

}
