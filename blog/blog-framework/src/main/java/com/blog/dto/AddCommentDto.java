package com.blog.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "添加评论-DTO")
public class AddCommentDto {
    /**
     * 评论类型（0代表文章评论，1代表友链评论）
     */
    @ApiModelProperty(value = "评论类型（0代表文章评论，1代表友链评论）", example = "0")
    private String type;

    /**
     * 文章id
     */
    @ApiModelProperty(value = "评论所属文章/友链id", example = "1")
    private Long articleId;

    /**
     * 根评论id
     */
    @ApiModelProperty(value = "根评论id\n若当前评论为根评论，则该字段为-1，否则为根评论id\n当前评论若为根评论时，to_comment_id与to_comment_user_id必须为-1\n", example = "1")
    private Long rootId;

    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容", example = "你好")
    private String content;

    /**
     * 回复目标评论id
     */
    @ApiModelProperty(value = "被评论id\n若不为根评论，则to_comment_id必须是已有评论的id，并且to_comment_id的comment_id与to_comment_user_id的user_id必须相同", example = "1")
    private Long toCommentId;
}
