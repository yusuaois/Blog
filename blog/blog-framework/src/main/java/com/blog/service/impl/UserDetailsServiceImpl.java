package com.blog.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blog.entity.LoginUser;
import com.blog.entity.User;
import com.blog.mapper.SysUserMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);
        // 根据用户名查询用户信息
        User user = userMapper.selectOne(queryWrapper);
        // 判断是否查到用户信息，如果没有查到就抛出异常
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        // 如果有查到用户信息，封装成UserDetails对象返回
        //TODO 查询权限信息封装
        return new LoginUser(user);
    }
}
