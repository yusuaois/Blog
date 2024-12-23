package com.blog.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "角色-更新-VO")
public class RoleUpdateVo {
    private List<MenuTreeSelectVo> menus;
    private List<Long> checkedKeys;
}
