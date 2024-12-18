package com.blog.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

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
public class LinkDto  {

    private Long id;
    private String name;
    private String logo;
    private String description;
    private String address;
    private String status;
}
