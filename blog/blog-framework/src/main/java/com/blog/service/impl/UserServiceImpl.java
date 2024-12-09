package com.blog.service.impl;

import com.blog.entity.User;
import com.blog.mapper.SysUserMapper;
import com.blog.service.UserService;
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

}
