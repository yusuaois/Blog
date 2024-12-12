package com.blog.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
import com.blog.service.MenuService;
import com.blog.service.RoleService;
import com.blog.service.SystemLoginService;
import com.blog.utils.BeanCopyUtils;
import com.blog.utils.SecurityUtils;
import com.blog.vo.AdminUserInfoVo;
import com.blog.vo.RoutersVo;
import com.blog.vo.UserInfoVo;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@RestController
public class SystemLoginController {
    @Autowired
    private SystemLoginService systemLoginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/user/login")
    @SystemLog(businessName = "后台登录")
    public ResponseResult login(@RequestBody User User) {
        if (!StringUtils.hasText(User.getUserName())) {
            // 提示必须输入用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return systemLoginService.login(User);
    }

    @RequestMapping("getInfo")
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

    @RequestMapping("getRouters")
    public ResponseResult<RoutersVo> getRouters() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //查询menu 结果是tree的形式（层级）
        List<SysMenu> menus = menuService.selectRouterMenuTreeByUserId(loginUser.getUser().getId());
        //封装数据并返回
    return ResponseResult.okResult(new RoutersVo(menus));
}

}
