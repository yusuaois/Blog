package com.blog.dto;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private Long id;
    private String roleName;
    private String roleKey;
    private Integer roleSort;
    private String status;
    private String remark;
    private List<Long> menuIds;


}
