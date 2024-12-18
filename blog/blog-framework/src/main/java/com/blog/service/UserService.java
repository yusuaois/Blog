package com.blog.service;

import com.blog.common.ResponseResult;
import com.blog.dto.AddUserDto;
import com.blog.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author AC
 * @since 2024-12-09
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);

    ResponseResult userList(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status);

    ResponseResult addUser(AddUserDto user);

    ResponseResult deleteUser(Long id);

    

}
