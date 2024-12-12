package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.constants.SystemConstants;
import com.blog.entity.SysMenu;
import com.blog.mapper.SysMenuMapper;
import com.blog.service.MenuService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Service
public class MenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements MenuService {


    @Override
    public List<String> selectPermsByUserId(Long id) {
        // 如果是管理员，则返回所有权限
        if (id == 1L) {
            LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(SysMenu::getMenuType, SystemConstants.MENU, SystemConstants.BUTTON);
            wrapper.eq(SysMenu::getStatus, SystemConstants.STATUS_NORMAL);
            List<SysMenu> menus = list(wrapper);
            List<String> perms = menus.stream().map(SysMenu::getPerms).collect(Collectors.toList());
            return perms;
        }
        // 否则返回其所具有的权限
        return getBaseMapper().selectPermsByUserId(id);
    }
}
