package com.blog.service;

import org.springframework.web.multipart.MultipartFile;

import com.blog.common.ResponseResult;

public interface UploadService {

    ResponseResult uploadImg(MultipartFile img);

}
