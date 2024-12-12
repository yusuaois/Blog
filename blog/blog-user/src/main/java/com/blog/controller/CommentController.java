package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.annotation.SystemLog;
import com.blog.common.ResponseResult;
import com.blog.constants.SystemConstants;
import com.blog.entity.Comment;
import com.blog.service.CommentService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    
    @Autowired
    private CommentService commentService;

    @RequestMapping("/commentList") 
    @SystemLog(businessName = "获取评论列表")
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }

    @RequestMapping
    @SystemLog(businessName = "获取评论列表")
    public ResponseResult addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @RequestMapping("/linkCommentList")
    @SystemLog(businessName = "获取友链评论列表")
    public ResponseResult linkCommentList(@RequestParam Integer pageNum, Integer pageSize) {
        return commentService.commentList(SystemConstants.LINK_COMMENT,null,pageNum, pageSize);
    }
    
    
}
