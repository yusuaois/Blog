package com.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.blog.mapper")
public class BlogUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogUserApplication.class, args);
    }
}


// @ComponentScan(basePackages = { 
//     "com.blog.controller","com.blog.mapper", 
//     "com.blog.service","com.blog.entity", 
//     "com.blog.config","com.blog.common", 
//     "com.blog.util","com.blog.config.redis", 
//     "com.blog.utils.redis","com.blog.filter", 
//     "com.blog.handler","com.blog.handler.security", 
//     "com.blog.exception","com.blog.handler.exceptionHandler", 
//     "com.blog.handler.mybatisPlus","com.blog.aspect",
//     "com.blog.annotation","com.blog.runner"
// })