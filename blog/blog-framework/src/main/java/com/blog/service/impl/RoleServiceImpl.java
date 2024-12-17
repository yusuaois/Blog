package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.ResponseResult;
import com.blog.entity.SysRole;
import com.blog.mapper.SysRoleMapper;
import com.blog.service.RoleService;
import com.blog.utils.BeanCopyUtils;
import com.blog.vo.PageVo;
import com.blog.vo.RoleVo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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
        queryWrapper.like(SysRole::getRoleName, roleName);
        queryWrapper.eq(SysRole::getStatus, status);
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
}
