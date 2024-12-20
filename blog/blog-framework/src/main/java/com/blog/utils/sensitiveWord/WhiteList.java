package com.blog.utils.sensitiveWord;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.github.houbb.sensitive.word.api.IWordAllow;

@Component
public class WhiteList implements IWordAllow {

    @Override
    public List<String> allow() {
        List<String> list = new ArrayList<>();
        // TODO 在数据库里查询拿出敏感词list
        // List<String> list = mapper.selectList();
        // list.add("五星红旗");
        // list.add("天安门");
        return list;
    }

}
