package com.blog.vo;

import java.util.List;

import com.blog.entity.SysRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AdminDetailVo {

    private List<Long> roleIds;

    private List<SysRole> roles;

    private UserInfoVo user;
}
