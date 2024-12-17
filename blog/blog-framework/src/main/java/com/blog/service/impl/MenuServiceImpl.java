package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.ResponseResult;
import com.blog.constants.SystemConstants;
import com.blog.entity.SysMenu;
import com.blog.mapper.SysMenuMapper;
import com.blog.service.MenuService;
import com.blog.utils.SecurityUtils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    @Autowired
    private SysMenuMapper menuMapper;
    
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

    @Override
    public List<SysMenu> selectRouterMenuTreeByUserId(Long userId) {
        SysMenuMapper menuMapper = getBaseMapper();
        List<SysMenu> menus;
        // 判断是否为管理员
        // 如果是返回所有符合要求的menu
        if (SecurityUtils.isAdmin()) {
            menus = menuMapper.selectAllRouterMenu();
        } else {
            // 否则返回当前用户所具有的menu
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }

        // 构建tree
        // 先找出第一层的菜单
        // 然后去找他们的子菜单，设置到children
        List<SysMenu> menuTree = builderMenuTree(menus,0L);
        return menuTree;
    }

    private List<SysMenu> builderMenuTree(List<SysMenu> menus, Long parentId) {
        List<SysMenu> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;
    }

    private List<SysMenu> getChildren(SysMenu menu, List<SysMenu> menus) {
        List<SysMenu> childrenList = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m -> m.setChildren(getChildren(m, menus)))
                .collect(Collectors.toList());
        return childrenList;
    }

    @Override
    public ResponseResult listAllMenu(String menuName,String status){
        //TODO 不需要建菜单树，直接返回所有菜单
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(menuName),SysMenu::getMenuName,menuName);
        queryWrapper.eq(StringUtils.hasText(status),SysMenu::getStatus,status);
        //TODO 菜单要按照父菜单id和orderNum进行排序
        queryWrapper.orderByAsc(SysMenu::getParentId,SysMenu::getOrderNum);
        List<SysMenu> menus = menuMapper.selectList(queryWrapper);
        return ResponseResult.okResult(menus);
    }
}
