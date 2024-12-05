package com.blog.utils;

import com.blog.entity.Article;
import org.springframework.beans.BeanUtils;
import com.blog.vo.HotArticleVo;
import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    private BeanCopyUtils() {
    }

    // 实现属性拷贝 BeanUtils
    public static <V> V copyBean(Object source, Class<V> target) {
        V result = null;
        try {
            // 创建目标对象
            result = target.newInstance();
            // 实现属性拷贝
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回结果
        return result;
    }

    public static <O,V> List<V> copyBeanList(List<O> sourceList, Class<V> target){
        return sourceList.stream().map(e->copyBean(e,target)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Article article = new Article();
        article.setId(1L);
        article.setTitle("Java");

        HotArticleVo articleVo = copyBean(article, HotArticleVo.class);
        System.out.println(articleVo);
    }
}
