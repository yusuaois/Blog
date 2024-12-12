package com.blog.vo;

import java.util.List;

import com.blog.entity.SysMenu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutersVo {

    private List<SysMenu> menus;
}