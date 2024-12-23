package com.blog.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户登录-VO")
public class BlogUserLoginVo {

    private String token;
    private UserInfoVo userInfo;
}