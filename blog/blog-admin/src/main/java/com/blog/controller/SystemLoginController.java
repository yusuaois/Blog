package com.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import com.blog.annotation.SystemLog;
import com.blog.common.AppHttpCodeEnum;
import com.blog.common.ResponseResult;
import com.blog.entity.LoginUser;
import com.blog.entity.SysMenu;
import com.blog.entity.User;
import com.blog.exception.SystemException;
import com.blog.vo.AdminUserInfoVo;
import com.blog.service.MenuService;
import com.blog.service.RoleService;
import com.blog.service.SystemLoginService;
import com.blog.utils.BeanCopyUtils;
import com.blog.utils.SecurityUtils;
import com.blog.vo.PageVo;
import com.blog.vo.RoutersVo;
import com.blog.vo.UserInfoVo;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Api(tags = "用户管理")
@RestController
public class SystemLoginController {
    @Autowired
    private SystemLoginService systemLoginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/user/login")
    @SystemLog(businessName = "后台登录")
    @Operation(summary = "用户登录")
    public ResponseResult login(@RequestBody User User) {
        if (!StringUtils.hasText(User.getUserName())) {
            // 提示必须输入用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return systemLoginService.login(User);
    }

    @GetMapping("getInfo")
    @Operation(summary = "获取用户信息")
    public ResponseResult<AdminUserInfoVo> getInfo() {
        // 获取当前登录用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        // 根据用户ID查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        // 根据用户id去查询角色信息0
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());

        // 获取用户信息
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        // 封装数据并返回
        AdminUserInfoVo vo = new AdminUserInfoVo(perms, roleKeyList, userInfoVo);
        return ResponseResult.okResult(vo);
    }

    @GetMapping("getRouters")
    @Operation(summary = "获取前端路由")
    public ResponseResult<RoutersVo> getRouters() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        // 查询menu 结果是tree的形式（层级）
        List<SysMenu> menus = menuService.selectRouterMenuTreeByUserId(loginUser.getUser().getId());
        // 封装数据并返回
        return ResponseResult.okResult(new RoutersVo(menus));
    }

    @PostMapping("/user/logout")
    @Operation(summary = "退出登录")
    public ResponseResult<PageVo> logout() {
        return systemLoginService.logout();
    }

}
