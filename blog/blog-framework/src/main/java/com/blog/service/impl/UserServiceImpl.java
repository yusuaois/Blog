package com.blog.service.impl;

import com.blog.common.AppHttpCodeEnum;
import com.blog.common.ResponseResult;
import com.blog.dto.AddUserDto;
import com.blog.entity.SysUserRole;
import com.blog.entity.User;
import com.blog.exception.SystemException;
import com.blog.mapper.SysUserMapper;
import com.blog.service.SysUserRoleService;
import com.blog.service.UserService;
import com.blog.utils.BeanCopyUtils;
import com.blog.utils.SecurityUtils;
import com.blog.vo.PageVo;
import com.blog.vo.UserInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SysUserRoleService userRoleService;

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
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult register(User user) {
        // 对数据进行非空判断
        if (!StringUtils.hasText(user.getUserName()))
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        if (!StringUtils.hasText(user.getPassword()))
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        if (!StringUtils.hasText(user.getNickName()))
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        if (!StringUtils.hasText(user.getEmail()))
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);

        // 对数据进行重复判断
        if (UserNameExist(user.getUserName()))
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        if (EmailExist(user.getEmail()))
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        if (NicknameExist(user.getNickName()))
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);

        // 对密码进行加密处理
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        // 存入数据库
        save(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult userList(Integer pageNum, Integer pageSize, String userName, String phonenumber,
            String status) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>(); // 模糊查询
        queryWrapper.like(StringUtils.hasText(userName), User::getUserName, userName);
        queryWrapper.like(StringUtils.hasText(phonenumber), User::getPhonenumber, phonenumber);
        queryWrapper.like(StringUtils.hasText(status), User::getStatus, status);
        // 分页
        Page<User> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper); // 获取分页数据
        PageVo vo = new PageVo(page.getRecords(), page.getTotal()); // 封装分页数据
        return ResponseResult.okResult(vo); // 返回分页数据
    }

    @Override
    public ResponseResult addUser(AddUserDto user) {
        User userInfo = BeanCopyUtils.copyBean(user, User.class);
        register(userInfo);
        SysUserRole userRole = new SysUserRole(userInfo.getId(), null);
        for (Long roleId : user.getRoleIds()) {
            userRole.setRoleId(roleId);
            userRoleService.save(userRole);
        }
        return ResponseResult.okResult();
    }

    private boolean UserNameExist(String userName) {
        return lambdaQuery().eq(User::getUserName, userName).count() > 0;
    }

    private boolean EmailExist(String email) {
        return lambdaQuery().eq(User::getEmail, email).count() > 0;
    }

    private boolean NicknameExist(String nickName) {
        return lambdaQuery().eq(User::getNickName, nickName).count() > 0;
    }

    public ResponseResult deleteUser(Long id) {
        //不能删除当前操作用户
        if(SecurityUtils.getUserId().equals(id))throw new SystemException(AppHttpCodeEnum.CAN_NOT_DELETE_YOURSELF);
        removeById(id); 
        return ResponseResult.okResult();
    }

}
