package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.AppHttpCodeEnum;
import com.blog.common.ResponseResult;
import com.blog.constants.SystemConstants;
import com.blog.entity.SysMenu;
import com.blog.entity.SysRoleMenu;
import com.blog.exception.SystemException;
import com.blog.mapper.SysMenuMapper;
import com.blog.service.MenuService;
import com.blog.service.SysRoleMenuService;
import com.blog.utils.BeanCopyUtils;
import com.blog.utils.SecurityUtils;
import com.blog.utils.WordDetectUtils;
import com.blog.vo.MenuTreeSelectVo;
import com.blog.vo.MenuVo;
import com.blog.vo.RoleUpdateVo;
import com.blog.vo.RoleVo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Autowired
    private SysRoleMenuService roleMenuService;

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
        List<SysMenu> menuTree = builderMenuTree(menus, 0L);
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
    public ResponseResult listAllMenu(String menuName, String status) {

        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(menuName), SysMenu::getMenuName, menuName);
        queryWrapper.eq(StringUtils.hasText(status), SysMenu::getStatus, status);

        queryWrapper.orderByAsc(SysMenu::getParentId, SysMenu::getOrderNum);
        List<SysMenu> menus = menuMapper.selectList(queryWrapper);

        List<MenuVo> vo = BeanCopyUtils.copyBeanList(menus, MenuVo.class);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult<SysMenu> addNewMenu(SysMenu menu) {
        // 为空
        if (!StringUtils.hasText(menu.getMenuName()))
            throw new SystemException(AppHttpCodeEnum.INPUT_FORMAT_ERROR);
        // 敏感词
        WordDetectUtils.checkSensitiveWord(menu.getMenuName());
        save(menu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult selectMenuById(Long id) {
        SysMenu menu = getById(id);
        MenuVo vo = BeanCopyUtils.copyBean(menu, MenuVo.class);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateMenuById(SysMenu menu) {
        // 为空
        if (!StringUtils.hasText(menu.getMenuName()))
            throw new SystemException(AppHttpCodeEnum.INPUT_FORMAT_ERROR);
        // 敏感词
        WordDetectUtils.checkSensitiveWord(menu.getMenuName());

        // 若设置父菜单为当前菜单
        // 提示“修改菜单'{Menu}'失败，上级菜单不能选择自己”
        if (menu.getParentId().equals(menu.getId())) {
            // throw new RuntimeException("修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,
                    "修改菜单\"" + menu.getMenuName() + "\"失败，上级菜单不能选择自己");
        }
        updateById(menu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteMenuById(Long menuId) {
        if (count(new QueryWrapper<SysMenu>().eq("parent_id", menuId)) > 0) {
            // throw new RuntimeException("删除菜单'" + menu.getMenuName() + "'失败，该菜单存在子菜单");
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,
                    "删除菜单\"" + getById(menuId).getMenuName() + "\"失败，该菜单存在子菜单");
        }
        removeById(menuId);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult treeSelect() {
        List<SysMenu> menus = list(new QueryWrapper<SysMenu>().orderByAsc("order_num"));
        // 构建菜单树
        List<SysMenu> menuTree = builderMenuTree(menus, 0L);
        // 转换为vo
        List<MenuTreeSelectVo> vo = transToVoChild(menuTree);
        return ResponseResult.okResult(vo);
    }

    private List<MenuTreeSelectVo> transToVoChild(List<SysMenu> menuTree) {
        List<MenuTreeSelectVo> vo = new ArrayList<>();
        for (SysMenu menu : menuTree) {
            MenuTreeSelectVo menuTreeSelectVo = new MenuTreeSelectVo();
            // 手动复制属性
            menuTreeSelectVo.setChildren(transToVoChild(menu.getChildren()));
            menuTreeSelectVo.setId(menu.getId());
            menuTreeSelectVo.setLabel(menu.getMenuName());
            menuTreeSelectVo.setParentId(menu.getParentId());
            if (menu.getChildren().size() > 0) {
                menuTreeSelectVo.setChildren(transToVoChild(menu.getChildren()));
            }
            vo.add(menuTreeSelectVo);
        }
        return vo;
    }

    @Override
    public ResponseResult roleMenuTreeselect(Long id) {
        // 全部菜单
        List<SysMenu> menu = list(new QueryWrapper<SysMenu>().orderByAsc("order_num"));
        List<SysMenu> menuTree = builderMenuTree(menu, 0L);
        List<MenuTreeSelectVo> menus = transToVoChild(menuTree);

        // 按照id查询菜单
        LambdaQueryWrapper<SysRoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleMenu::getRoleId, id);
        List<SysRoleMenu> tmp = roleMenuService.list(wrapper);
        List<Long> checkedKeys = tmp.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());

        // 封装为VO
        RoleUpdateVo vo = new RoleUpdateVo(menus, checkedKeys);
        return ResponseResult.okResult(vo);
    }
}
