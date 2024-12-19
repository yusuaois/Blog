package com.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.blog.utils.sensitiveWord.BlackList;
import com.blog.utils.sensitiveWord.WhiteList;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.deny.WordDenys;

@Configuration
public class SpringSensitiveWordConfig {

    @Autowired
    private WhiteList whiteList;

    @Autowired
    private BlackList blackList;

    /**
     * 初始化引导类
     * 
     * @return 初始化引导类
     * @since 1.0.0
     */
    @Bean
    public SensitiveWordBs sensitiveWordBs() {
        SensitiveWordBs init = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.chains(WordAllows.system(), whiteList))
                .wordDeny(WordDenys.chains(WordDenys.system(), blackList))
                // 忽略大小写
                .ignoreCase(true)
                // 忽略全角半角
                .ignoreWidth(true)
                // 忽略数字写法
                .ignoreNumStyle(true)
                // 忽略中文书写格式
                .ignoreChineseStyle(true)
                // 忽略英文书写格式
                .ignoreEnglishStyle(true)
                // 忽略重复
                .ignoreRepeat(true)
                // 启用数字检查 默认连续8位数字为敏感词
                .enableNumCheck(true)
                // 启用邮箱检查
                .enableEmailCheck(true)
                // 启用url检查
                .enableUrlCheck(true)
                // 各种其他配置
                .init();

        return init;
    }
}
