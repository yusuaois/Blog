package com.blog.vo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "友链-VO")
public class LinksVo {
    private Long id;
    private String name;
    private String logo;
    private String description;
    private String address;
    private String status;
}
