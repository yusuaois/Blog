package com.blog.vo;

import java.util.List;

import com.blog.entity.SysRole;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "管理员详情-VO")
public class AdminDetailVo {

    private List<Long> roleIds;

    private List<SysRole> roles;

    private UserInfoVo user;
}
