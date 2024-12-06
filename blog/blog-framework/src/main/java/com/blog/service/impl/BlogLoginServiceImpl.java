package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.ResponseResult;
import com.blog.entity.LoginUser;
import com.blog.entity.User;
import com.blog.mapper.SysUserMapper;
import com.blog.service.BlogLoginService;
import com.blog.utils.BeanCopyUtils;
import com.blog.utils.redis.JwtUtil;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.blog.utils.redis.RedisCache;
import com.blog.vo.BlogUserLoginVo;
import com.blog.vo.UserInfoVo;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Service
public class BlogLoginServiceImpl extends ServiceImpl<SysUserMapper, User> implements BlogLoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;
 
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        
        //判断是否认证通过
        if(Objects.isNull(authenticate))throw new RuntimeException("用户名或密码错误");
        
        //获取userid 生成用户token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        
        //把用户信息存入redis中
        redisCache.setCacheObject("bloglogin:"+userId, loginUser);
        
        //把token和userinfo封装成ResponseResult返回
        //把user转换为UserInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        BlogUserLoginVo blogUserLoginVo = new BlogUserLoginVo(jwt, userInfoVo);
        return ResponseResult.okResult(blogUserLoginVo);
    }
}
