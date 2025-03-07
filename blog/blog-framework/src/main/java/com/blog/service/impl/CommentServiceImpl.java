package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.AppHttpCodeEnum;
import com.blog.common.ResponseResult;
import com.blog.constants.SystemConstants;
import com.blog.dto.AddCommentDto;
import com.blog.entity.Comment;
import com.blog.entity.LoginUser;
import com.blog.entity.User;
import com.blog.exception.SystemException;
import com.blog.mapper.CommentMapper;
import com.blog.service.CommentService;
import com.blog.service.UserService;
import com.blog.utils.BeanCopyUtils;
import com.blog.utils.SecurityUtils;
import com.blog.utils.WordDetectUtils;
import com.blog.vo.CommentVo;
import com.blog.vo.PageVo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

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
  public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
    // 查询对应文章的根评论
    LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();

    // 对article进行判断
    queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType), Comment::getArticleId, articleId);

    // 根评论 rootId为-1
    queryWrapper.eq(Comment::getRootId, -1);

    // 过滤匿名评论 createBy字段不为-1
    queryWrapper.ne(Comment::getCreateBy, -1);

    // 评论类型
    queryWrapper.eq(Comment::getType, commentType);

    // 分页查询
    Page<Comment> page = new Page<>(pageNum, pageSize);
    page(page, queryWrapper);

    List<CommentVo> commentVolist = toCommentVoList(page.getRecords());

    // 查询所有根评论对应的子评论的集合
    for (CommentVo commentVo : commentVolist) {
      List<CommentVo> childrComments = getChildren(commentVo.getId());
      commentVo.setChildren(childrComments);
    }

    return ResponseResult.okResult(new PageVo(commentVolist, page.getTotal()));
  }

  // @Param id 根评论id
  // @return
  // 根据评论的id查找子评论
  private List<CommentVo> getChildren(Long id) {
    LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Comment::getRootId, id);

    queryWrapper.orderByAsc(Comment::getCreateTime);

    List<Comment> comments = list(queryWrapper);
    List<CommentVo> commentVos = toCommentVoList(comments);
    return commentVos;
  }

  private List<CommentVo> toCommentVoList(List<Comment> comments) {
    List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(comments, CommentVo.class);
    // 遍历Vo集合
    for (CommentVo commentVo : commentVos) {
      String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
      commentVo.setUsername(nickName);
      // 根据创建人id查询创建人信息
      // 如果toCommentId不为-1才进行查询
      if (commentVo.getToCommentId() != -1) {
        String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();
        commentVo.setToCommentUserName(toCommentUserName);
      }
    }

    // 通过createBy查询用户名称并赋值
    // 通过toCommentUserId查询用户名称并赋值
    return commentVos;
  }

  @Override
  public ResponseResult addComment(@RequestBody AddCommentDto comment) {
 
    // 安全性检测 根据上下文判断当前是否登录
    try {
      SecurityUtils.getLoginUser();
    } catch (Exception e) {
      throw new SystemException(AppHttpCodeEnum.NEED_LOGIN);
    }

    // 内容不为空
    if ((!StringUtils.hasText(comment.getContent())))
      throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
    // 敏感词检测
    WordDetectUtils.checkSensitiveWord(comment.getContent());
    // 检测当前评论若为根评论时，to_comment_id必须为-1
    if (comment.getRootId() == SystemConstants.ROOT_COMMENT) {
      if (comment.getToCommentId() != -1L) {
        throw new SystemException(AppHttpCodeEnum.COMMENT_ERROR);
      }
    }
    // 检测父评论id是否存在
    if (comment.getRootId() != SystemConstants.ROOT_COMMENT) {
      Comment rootComment = getById(comment.getRootId());
      if (rootComment == null) {
        throw new SystemException(AppHttpCodeEnum.ROOT_COMMENT_NOT_EXIST);
      }
    }
    // 检测to_comment_id是否存在，并且to_comment_id的comment_id与to_comment_user_id的user_id是否相同
    if (comment.getToCommentId() != SystemConstants.ROOT_COMMENT) {
      Comment toComment = getById(comment.getToCommentId());
      if (toComment == null) {
        throw new SystemException(AppHttpCodeEnum.TO_COMMENT_NOT_EXIST);
      }
    }
    
    Comment newComment = BeanCopyUtils.copyBean(comment, Comment.class);
    if (comment.getToCommentId() != -1L)
      newComment.setToCommentUserId(getById(comment.getToCommentId()).getCreateBy());
    else
      newComment.setToCommentUserId(-1L);
    save(newComment);
    return ResponseResult.okResult();
  }
}
