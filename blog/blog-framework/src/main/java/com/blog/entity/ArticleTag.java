package com.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Data;


/**
 * <p>
 * 文章标签关联表
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Data
@TableName("sg_article_tag")
public class ArticleTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    @TableId(value = "article_id", type = IdType.AUTO)
    private Long articleId;

    /**
     * 标签id
     */

    private Long tagId;
}
