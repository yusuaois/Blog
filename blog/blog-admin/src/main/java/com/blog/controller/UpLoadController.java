package com.blog.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.annotation.SystemLog;
import com.blog.common.ResponseResult;
import com.blog.service.UploadService;

@RestController
public class UpLoadController {
    @Autowired
    private UploadService uploadService;

    @RequestMapping("/upload")
    @SystemLog(businessName = "上传文件")
    public ResponseResult uploadImg(MultipartFile img){
        try {
            return uploadService.uploadImg(img);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件上传失败");
        }
    }
}
