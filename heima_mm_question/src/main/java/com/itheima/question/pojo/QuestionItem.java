package com.itheima.question.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("st_question_item")
public class QuestionItem {
    @TableId(type = IdType.ASSIGN_UUID)
    String id;
    String questionId;
    String content;
    String imgUrl;
    String isRight;
    String picture;
}
