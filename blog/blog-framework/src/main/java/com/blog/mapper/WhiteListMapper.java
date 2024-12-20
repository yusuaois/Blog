package com.blog.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WhiteListMapper extends BaseMapper {
    public List<String> whiteList();
}
