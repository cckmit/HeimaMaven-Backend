
package com.itheima.question.vo;

import com.itheima.question.pojo.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionVo {
    private String id;
    private String companyName;
    private String catalogName;
    private String subject;
    private String type;
    private String difficulty;
    private String isClassic;
    private String state;
    private String reviewStatus;

    public static QuestionVo fromQuestion(Question question) {
        QuestionVo questionVo = new QuestionVo();
        BeanUtils.copyProperties(question, questionVo);
        return questionVo;
    }
}
