package com.blog.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "分类-VO")
public class CategoryVo {
    private Long id;
    private String name;
    private String description;
    private String status;
}
