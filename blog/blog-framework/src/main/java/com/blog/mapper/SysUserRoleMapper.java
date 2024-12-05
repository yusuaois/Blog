package com.blog.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.SysUserRole;

/**
 * <p>
 * 用户和角色关联表 Mapper 接口
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

}
