package com.blog.runner;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.blog.constants.SystemConstants;
import com.blog.entity.Article;
import com.blog.mapper.ArticleMapper;
import com.blog.utils.redis.RedisCache;

@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    // 在程序启动时自动启用一次
    @Override
    public void run(String... args) throws Exception {
        // 查询博客信息 id viewcount
        List<Article> articles = articleMapper.selectList(null);
        Map<String,Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article->article.getId().toString(), article -> {
                    return article.getViewCount().intValue();
                }));
        // 存储到redis当中
        redisCache.setCacheMap(SystemConstants.ARTICLE_VIEW_COUNT, viewCountMap);
    }
}
