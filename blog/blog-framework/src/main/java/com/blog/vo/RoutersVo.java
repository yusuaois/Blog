package com.blog.vo;

import java.util.List;

import com.blog.entity.SysMenu;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "路由-VO")
public class RoutersVo {

    private List<SysMenu> menus;
}