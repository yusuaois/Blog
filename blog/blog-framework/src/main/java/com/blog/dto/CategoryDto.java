package com.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * <p>
 * 分类表
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {



    private Long id;

    /**
     * 分类名
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态0:正常,1禁用
     */
    private String status;


}