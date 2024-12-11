package com.blog.service.impl;

import com.blog.common.ResponseResult;
import com.blog.entity.User;
import com.blog.mapper.SysUserMapper;
import com.blog.service.UserService;
import com.blog.utils.BeanCopyUtils;
import com.blog.utils.SecurityUtils;
import com.blog.vo.UserInfoVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author AC
 * @since 2024-12-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<SysUserMapper, User> implements UserService {

    @Override
    public ResponseResult userInfo() {
        // 获取当前用户id
        Long userId = SecurityUtils.getUserId();
        // 根据用户id查询用户信息
        User user = getById(userId);
        // 封装成UserInfoVo
        UserInfoVo vo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateUserInfo(User user){
        updateById(user);
        return ResponseResult.okResult(); 
    }
}
