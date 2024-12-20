package com.blog.utils.sensitiveWord;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blog.mapper.BlackListMapper;
import com.github.houbb.sensitive.word.api.IWordDeny;

@Component
public class BlackList implements IWordDeny {
    @Autowired
    private BlackListMapper blackListMapper;

    @Override
    public List<String> deny() {
        List<String> list = new ArrayList<>();
        //在数据库里查询拿出敏感词list
        list = blackListMapper.blackList();
        // list.add("你好");
        return list;
    }

}
