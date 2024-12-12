package com.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.SysMenu;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<String> selectPermsByUserId(Long id);

    List<SysMenu> selectAllRouterMenu();

    List<SysMenu> selectRouterMenuTreeByUserId(Long userId);

}
