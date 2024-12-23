package com.blog.vo;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "文章列表-VO")
public class ArticleListVo {
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章摘要
     */
    private String summary;

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
