package com.blog.notUsed;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entity.SysUser;
import com.blog.service.ISysUserService;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    private ISysUserService sysUserService;

    @RequestMapping("/list")
    public List<SysUser> list() {
        return sysUserService.list();
    }
    
}
