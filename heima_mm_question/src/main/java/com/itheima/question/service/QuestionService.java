package com.itheima.question.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.admin.PageVo;
import com.itheima.question.dto.QuestionDto;
import com.itheima.question.dto.QuestionPageDto;
import com.itheima.question.pojo.Question;
import com.itheima.question.vo.QuestionVo;

public interface QuestionService extends IService<Question> {

    boolean addQuestion(QuestionDto questionDto);

    PageVo<QuestionVo> listQuestionVo(QuestionPageDto questionPageDto);
}
