package com.graduation.bird.controller;

import com.graduation.bird.entity.Result;
import com.graduation.bird.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> uploadFile(MultipartFile file) throws Exception {

        //把文件的内容存储到本地磁盘上
        String fileName = file.getOriginalFilename();

        //利用UUID保证文件的名字是唯一的，防止覆盖
        fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));

//        file.transferTo(new File("C:\\Users\\Ben\\Desktop\\files\\" + fileName));
        //调用工具类上传文件
        String url = AliOssUtil.UploadFile(fileName, file.getInputStream());

        return Result.success(url);

    }

}
