package com.blog.vo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "热门文章-VO")
public class HotArticleVo {
    private Long id;
    private String title;
    private Long viewCount; 
    private Long likeCount;
}
