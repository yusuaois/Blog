package com.blog.vo;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "菜单权限树-VO")
public class MenuTreeSelectVo {
    // "children":[],
	// 		"id":"2023",
	// 		"label":"写博文",
	// 		"parentId":"0"

    private Long id;
    private String label;
    private Long parentId;
    private List<MenuTreeSelectVo> children;
}
