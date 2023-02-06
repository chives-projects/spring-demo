package com.csc.spring.demo.hello.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author: csc
 * @create: 2021/5/22 17:01
 */
@RestController
@Api(tags = "文件操作")
@RequestMapping("file")
public class FileController {
    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        if (file == null) {
            return "上传失败，未选择文件";
        }
        String fileName = file.getOriginalFilename();
        System.out.println("文件名：" + fileName);
        String filePath = "F:\\file\\";
        File dest = new File(filePath + fileName);
        try {
            file.getInputStream();
            file.transferTo(dest);
            System.out.println("上传成功！");
        } catch (IOException e) {
            System.out.println("上传异常！" + e);
            return "error";
        }
        return "success";
    }
}
