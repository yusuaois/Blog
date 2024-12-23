package com.blog.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * <p>
 * 友链
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "友链-DTO")
public class LinkDto  {

    private Long id;
    private String name;
    private String logo;
    private String description;
    private String address;
    private String status;
}
