package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.SysUser;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}
