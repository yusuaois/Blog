package com.blog.vo;

import java.util.List;

import com.blog.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AliveUserVo {
    List<User> user;
    Long total;
}
