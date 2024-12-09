package com.blog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.blog.controller", "com.blog.mapper", "com.blog.service", "com.blog.entity", "com.blog.config", "com.blog.common", "com.blog.util", "com.blog.config.redis", "com.blog.utils.redis", "com.blog.filter"})
@MapperScan("com.blog.mapper")
public class BlogUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogUserApplication.class, args);
    }
}
