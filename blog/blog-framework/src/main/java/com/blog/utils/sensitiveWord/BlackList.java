package com.blog.utils.sensitiveWord;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.github.houbb.sensitive.word.api.IWordDeny;

@Component
public class BlackList implements IWordDeny {

    @Override
    public List<String> deny() {
        List<String> list = new ArrayList<>();
        //list.add("你好");
        return list;
    }

}
