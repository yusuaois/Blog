package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.common.ResponseResult;
import com.blog.dto.AddArticleDto;
import com.blog.entity.Article;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */
public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult add(AddArticleDto article);

    ResponseResult selectArticleList(Integer pageNum, Integer pageSize, String title, String summary);
}
