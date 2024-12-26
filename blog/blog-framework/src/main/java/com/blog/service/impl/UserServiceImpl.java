package com.blog.service.impl;

import com.blog.common.AppHttpCodeEnum;
import com.blog.common.ResponseResult;
import com.blog.dto.UserDto;
import com.blog.entity.SysRole;
import com.blog.entity.SysUserRole;
import com.blog.entity.User;
import com.blog.exception.SystemException;
import com.blog.mapper.SysUserMapper;
import com.blog.service.RoleService;
import com.blog.service.SysUserRoleService;
import com.blog.service.UserService;
import com.blog.utils.BeanCopyUtils;
import com.blog.utils.SecurityUtils;
import com.blog.utils.WordDetectUtils;
import com.blog.vo.PageVo;
import com.blog.vo.UserInfoVo;
import com.blog.vo.AdminDetailVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

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
    @Autowired
    private RoleService roleService;

    private boolean UserNameExist(String userName) {
        return lambdaQuery().eq(User::getUserName, userName).count() > 0;
    }

    private boolean EmailExist(String email) {
        return lambdaQuery().eq(User::getEmail, email).count() > 0;
    }

    private boolean NicknameExist(String nickName) {
        return lambdaQuery().eq(User::getNickName, nickName).count() > 0;
    }

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
        // 为空
        if (!StringUtils.hasText(user.getNickName()))
            throw new SystemException(AppHttpCodeEnum.INPUT_FORMAT_ERROR);
        // 敏感词
        WordDetectUtils.checkSensitiveWord(user.getNickName());
        // 更新用户信息
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
        // if (!StringUtils.hasText(user.getNickName()))
        //     throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        // if (!StringUtils.hasText(user.getEmail()))
        //     throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);

        // 对用户名进行是否仅含有英文、数字判断
        if (WordDetectUtils.containsOnlyEnglishAndNumber(user.getUserName()))
            throw new SystemException(AppHttpCodeEnum.INPUT_FORMAT_ERROR);

        // 对密码进行是否仅含有英文、数字与下划线判断
        if (WordDetectUtils.containsOnlyEnglishAndNumberAndUnderline(user.getPassword()))
            throw new SystemException(AppHttpCodeEnum.INPUT_FORMAT_ERROR);

        // // 对邮箱进行格式判断
        // if (!WordDetectUtils.isEmail(user.getEmail()))
        //     throw new SystemException(AppHttpCodeEnum.INPUT_FORMAT_ERROR);

        // // 敏感词
        // WordDetectUtils.checkSensitiveWord(user.getNickName());

        // 对数据进行重复判断
        if (UserNameExist(user.getUserName()))
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        // if (EmailExist(user.getEmail()))
        //     throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        // if (NicknameExist(user.getNickName()))
        //     throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);

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
    public ResponseResult addUser(UserDto user) {
        User userInfo = BeanCopyUtils.copyBean(user, User.class);
        register(userInfo);
        SysUserRole userRole = new SysUserRole(userInfo.getId(), null);
        for (Long roleId : user.getRoleIds()) {
            userRole.setRoleId(roleId);
            userRoleService.save(userRole);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteUser(Long id) {
        // 不能删除当前操作用户
        if (SecurityUtils.getUserId().equals(id))
            throw new SystemException(AppHttpCodeEnum.CAN_NOT_DELETE_YOURSELF);
        removeById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult userDetail(Long id) {
        // roleIds：用户所关联的角色id列表
        List<Long> roleIds = userRoleService.list(new QueryWrapper<SysUserRole>().eq("user_id", id)).stream()
                .map(SysUserRole::getRoleId).collect(Collectors.toList());
        // roles：所有角色的列表
        List<SysRole> roles = (List<SysRole>) roleService.listAllRole().getData();
        // user：用户信息
        User user = getById(id);
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        AdminDetailVo vo = new AdminDetailVo(roleIds, roles, userInfoVo);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateUser(UserDto user) {
        // 为空
        if (!StringUtils.hasText(user.getNickName()))
            throw new SystemException(AppHttpCodeEnum.INPUT_FORMAT_ERROR);
        // 敏感词
        WordDetectUtils.checkSensitiveWord(user.getNickName());
        // 更新用户信息
        updateById(BeanCopyUtils.copyBean(user, User.class));
        // 更新用户角色信息
        userRoleService.removeById(user.getId());// 先删除用户角色信息
        SysUserRole userRole = new SysUserRole(user.getId(), null);
        for (Long role : user.getRoleIds()) {// 再添加用户角色信息
            userRole.setRoleId(role);
            userRoleService.save(userRole);
        }
        return ResponseResult.okResult();
    }

}
