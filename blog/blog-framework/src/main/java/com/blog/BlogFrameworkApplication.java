package com.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.blog.controller", "com.blog.mapper", "com.blog.service", "com.blog.entity"})
@MapperScan("com.blog.mapper")
public class BlogFrameworkApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogFrameworkApplication.class, args);
    }
}