package com.itheima.question.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.itheima.question.dto.QuestionDto;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
@TableName("st_question")
public class Question {
    private String id;
    private String catalogId;
    private String catalogName;
    private String courseId;
    private String courseName;
    private String number;
    private String subject;
    private String type;
    private String difficulty;
    private String analysis;
    private String analysisVideo;
    private String remark;
    private String isClassic;
    private String state;
    private String reviewStatus;
    private String createBy;
    private String createDept;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String companyId;
    private String companyName;
    private String picture;

    public QuestionDto toQuestionDto() {
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(this, questionDto);
        return questionDto;
    }
}
