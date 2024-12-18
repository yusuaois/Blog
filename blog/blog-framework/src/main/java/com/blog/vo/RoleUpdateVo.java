package com.blog.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleUpdateVo {
    private List<MenuTreeSelectVo> menus;
    private List<Long> checkedKeys;
}
