package com.blog.handler.mybatisPlus;

import java.time.LocalDateTime;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.blog.entity.LoginUser;
import com.blog.utils.SecurityUtils;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = null;
        try {
            userId = SecurityUtils.getUserId();
        } catch (Exception e) {
            e.printStackTrace();
            userId = -1L;// 表示是自己创建
        }
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("createBy", userId, metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        LoginUser loginUser = SecurityUtils.getLoginUser();
        if(loginUser != null) {
            this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
            this.setFieldValByName("updateBy", SecurityUtils.getUserId(), metaObject);
        }
        else {
            this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
            this.setFieldValByName("updateBy", -1L, metaObject);
        }
        
    }
}
