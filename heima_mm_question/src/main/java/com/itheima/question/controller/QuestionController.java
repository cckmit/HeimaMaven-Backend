package com.itheima.question.controller;

import com.itheima.admin.PageVo;
import com.itheima.admin.Result;
import com.itheima.question.config.FastDFSClientUtil;
import com.itheima.question.dto.QuestionDto;
import com.itheima.question.dto.QuestionPageDto;
import com.itheima.question.pojo.Question;
import com.itheima.question.service.QuestionService;
import com.itheima.question.vo.OssVo;
import com.itheima.question.vo.QuestionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    FastDFSClientUtil fastDFSClientUtil;

    @Value("${fileServerUrl}")
    private String fileServerUrl;

    @Autowired
    private QuestionService questionService;

    @PostMapping("/add")
    public Result<Object> addQuestion(@RequestBody QuestionDto questionDto) {
        if (questionService.addQuestion(questionDto)) {
            return new Result<>(true, "添加成功", questionDto);
        }
        return new Result<>(false, "添加失败", null);
    }

    @PostMapping("/list")
    public PageVo<QuestionVo> listQuestionVo(@RequestBody QuestionPageDto questionPageDto) {
        return questionService.listQuestionVo(questionPageDto);
    }

    @GetMapping("/{id}")
    public QuestionDto getQuestionById(@PathVariable String id) {
        Question question = questionService.getById(id);
        return question.toQuestionDto();
    }

    @PostMapping("/upload")
    public OssVo uploadImg(MultipartFile file) {
        try {
            if (file == null) {
                throw new RuntimeException("文件不存在");
            }
            String fileId = fastDFSClientUtil.uploadFile(file);
            return OssVo.builder().imgUrl(fileServerUrl + fileId).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("文件上传失败");
    }
}
