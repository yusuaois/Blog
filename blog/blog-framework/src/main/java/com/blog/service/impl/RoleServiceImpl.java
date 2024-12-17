package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.ResponseResult;
import com.blog.dto.AddRoleDto;
import com.blog.entity.SysRole;
import com.blog.entity.SysRoleMenu;
import com.blog.entity.SysUserRole;
import com.blog.mapper.SysRoleMapper;
import com.blog.mapper.SysRoleMenuMapper;
import com.blog.service.RoleService;
import com.blog.service.SysRoleMenuService;
import com.blog.service.SysUserRoleService;
import com.blog.utils.BeanCopyUtils;
import com.blog.vo.PageVo;
import com.blog.vo.RoleVo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Service
public class RoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements RoleService {

    @Autowired
    private SysRoleMenuService roleMenuService;

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        // 判断是否为管理员，如果是返回集合中只需要有admin即可
        if (id.equals(1L)) {
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        // 否则查询当前用户所具有的角色信息
        return getBaseMapper().selectRoleKeyByUserId(id);
    }

    @Override
    public ResponseResult listRole(Integer pageNum, Integer pageSize, String roleName, String status) {
        // 需要有角色列表分页查询的功能。
        // ​ 要求能够针对角色名称进行模糊查询。
        // ​ 要求能够针对状态进行查询。
        // ​ 要求按照role_sort进行升序排列。
        // 封装为vo
        

        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        //当输入角色名称时，根据角色名称查询
        queryWrapper.like(StringUtils.hasText(roleName), SysRole::getRoleName, roleName);
        //当输入状态时，根据状态查询
        queryWrapper.eq(StringUtils.hasText(status), SysRole::getStatus, status);
        queryWrapper.orderByAsc(SysRole::getRoleSort);
        // 分页查询
        Page<SysRole> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        List<RoleVo> roles = page.getRecords().stream().map(role -> BeanCopyUtils.copyBean(role, RoleVo.class))
                .collect(Collectors.toList());
        // 封装返回
        PageVo pageVo = new PageVo(roles, page.getTotal());
        return ResponseResult.okResult(pageVo);

    }

    @Override
    public ResponseResult changeStatus(SysRole role) {
        // 需要有修改角色状态的功能。
        // ​ 要求能够根据角色id修改角色状态。
        // ​ 要求能够修改成功后返回成功信息。
        role.setId(role.getRoleId());
        updateById(role);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult addRole(AddRoleDto role) {
        // 角色名已存在 抛出异常
        if (StringUtils.hasText(role.getRoleName())) {
            LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SysRole::getRoleName, role.getRoleName());
            SysRole sysRole = getOne(queryWrapper);
            if (sysRole != null) {
                throw new RuntimeException("角色名已存在");
            }
        }
        // roleId和menuId的关系在表 sys_role_menu 中
        // 角色信息在表 sys_role 中
        SysRole sysRole = BeanCopyUtils.copyBean(role, SysRole.class);
        save(sysRole);

        // 保存权限和角色关系 
        SysRoleMenu roleMenu = BeanCopyUtils.copyBean(role, SysRoleMenu.class);
        roleMenu.setRoleId(sysRole.getId());
        //通过流将role中的menuIds取出
        List<Long> menuIds = role.getMenuIds().stream().map(Long::valueOf).collect(Collectors.toList());
        for(Long menuId : menuIds){
            roleMenu.setMenuId(menuId);
            roleMenuService.save(roleMenu);
        }
        return ResponseResult.okResult();
    }
}
