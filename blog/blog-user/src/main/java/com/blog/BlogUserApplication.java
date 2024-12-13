package com.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.blog.mapper")
public class BlogUserApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(BlogUserApplication.class, args);
    }
}