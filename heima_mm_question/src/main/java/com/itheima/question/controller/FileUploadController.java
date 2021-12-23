package com.itheima.question.controller;

import com.itheima.common.fastdfs.FastDFSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    FastDFSClientUtil fastDFSClientUtil;

    @Value("${fileServerUrl}")
    private String fileServerUrl;

    @PostMapping("/upload")
    public String uploadFile(MultipartFile file) {
        try {
            //判断文件是否存在
            if (file == null) {
                throw new RuntimeException("文件不存在");
            }
            String fileId = fastDFSClientUtil.uploadFile(file);
            return fileServerUrl + fileId;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "文件上传失败";

    }

    @GetMapping("/del")
    public String delFile(String fileId) {
        try {
            fastDFSClientUtil.delFile(fileId);
            return "删除成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "删除失败";
    }
}
