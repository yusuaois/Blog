package com.blog.vo;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "文章详情-VO")
public class ArticleDetailVo {
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章摘要
     */
    private String summary;

    //分类id
    private Long categoryId;

    //内容
    private String content;

    /**
     * 所属分类名
     */
    private String categoryName;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 访问量
     */
    private Long viewCount;

    private LocalDateTime createTime;
}
