package com.blog.job;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.blog.constants.SystemConstants;
import com.blog.entity.Article;
import com.blog.service.ArticleService;
import com.blog.utils.redis.RedisCache;

@Component
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0 0/5 * * * ? ")
    public void updateViewCount(){
        //获取redis中的浏览量
        Map<String,Integer> viewCount = redisCache.getCacheMap(SystemConstants.ARTICLE_VIEW_COUNT);
        List<Article> articles = viewCount.entrySet()
        .stream()
        .map(entry->new Article(Long.valueOf(entry.getKey()),entry.getValue().longValue()))
        .collect(Collectors.toList());
        //更新到数据库中
        articleService.updateBatchById(articles);
        //System.out.println("更新浏览量");
    }
}