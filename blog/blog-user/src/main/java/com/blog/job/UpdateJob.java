package com.blog.job;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.blog.constants.SystemConstants;
import com.blog.entity.Article;
import com.blog.service.ArticleService;
import com.blog.utils.redis.RedisCache;

@Component
public class UpdateJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0 0/5 * * * ? ")
    public void updateViewCountandLikeCount(){
        //获取redis中的浏览量
        Map<String,Integer> viewCount = redisCache.getCacheMap(SystemConstants.ARTICLE_VIEW_COUNT);
        //获取redis中的点赞量 
        Map<String,Integer> likeCount = redisCache.getCacheMap(SystemConstants.ARTICLE_LIKE_COUNT);
        
        List<Article> articles = viewCount.entrySet()
        .stream()
        .map(entry->new Article(Long.valueOf(entry.getKey()),entry.getValue().longValue(), likeCount.get(entry.getKey()).longValue()))
        .collect(Collectors.toList());
        //更新到数据库中
        // articleService.updateBatchById(articles);//全字段更新 会导致系统更新时没有token
        for(Article article:articles){
            LambdaUpdateWrapper<Article> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(Article::getId,article.getId());
            wrapper.set(Article::getViewCount,article.getViewCount());
            wrapper.set(Article::getLikeCount,article.getLikeCount());
            articleService.update(wrapper);
        }
        //打印list
        // System.out.println(articles);
        //System.out.println("更新浏览量");
    }
}