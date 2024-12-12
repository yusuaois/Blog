package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.common.ResponseResult;
import com.blog.entity.User;


/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
public interface AdminLoginService extends IService<User> {

     ResponseResult login(User user);



}
