package com.blog.vo;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "角色-VO")
public class RoleVo {
    // "id":"12",
    // "roleKey":"link",
    // "roleName":"友链审核员",
    // "roleSort":"1",
    // "status":"0"
    private Long id;
    private String roleKey;
    private String roleName;
    private Integer roleSort;
    private String status;
    private String remark;
    private LocalDateTime createTime;
}
