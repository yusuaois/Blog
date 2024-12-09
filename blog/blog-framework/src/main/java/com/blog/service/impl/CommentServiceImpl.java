package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.ResponseResult;
import com.blog.entity.Comment;
import com.blog.mapper.CommentMapper;
import com.blog.service.CommentService;
import com.blog.service.UserService;
import com.blog.utils.BeanCopyUtils;
import com.blog.vo.CommentVo;
import com.blog.vo.PageVo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
   @Autowired
   private UserService userService;
    
    @Override
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        // 查询对应文章的根评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        // 对article进行判断
        queryWrapper.eq(Comment::getArticleId, articleId);
        // 根评论 rootId为-1
        queryWrapper.eq(Comment::getRootId, -1);
        // 分页查询
        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);

        List<CommentVo> commentVolist = toCommentVoList(page.getRecords());
        //
        return ResponseResult.okResult(new PageVo(commentVolist, page.getTotal()));
    }

    private List<CommentVo> toCommentVoList(List<Comment> comments) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(comments, CommentVo.class);
        //遍历Vo集合
        for (CommentVo commentVo : commentVos) {
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);
            // 根据创建人id查询创建人信息
            //如果toCommentId不为-1才进行查询
            if (commentVo.getToCommentId() != -1) {
                String toCommentUserName = userService.getById(commentVo.getToCommentId()).getNickName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        }
        
        //通过createBy查询用户名称并赋值
        //通过toCommentUserId查询用户名称并赋值
        return commentVos;
    }
}
