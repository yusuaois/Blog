package com.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface BlackListMapper extends BaseMapper<String>{
    public List<String> blackList();
}
