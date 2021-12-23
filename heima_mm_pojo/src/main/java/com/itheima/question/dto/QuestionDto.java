package com.itheima.question.dto;

import com.itheima.question.pojo.Question;
import lombok.Data;

@Data
public class QuestionDto {
    private String companyId;
    private String catalogId;
    private String subject;
    private String type;
    private String difficulty;
    private String isClassic;
    private String state;
    private String analysis;
    private String picture;

    public Question toQuestion() {
        Question question = new Question();
        question.setCompanyId(companyId);
        question.setCatalogId(catalogId);
        question.setSubject(subject);
        question.setType(type);
        question.setDifficulty(difficulty);
        question.setIsClassic(isClassic);
        question.setState(state);
        question.setAnalysis(analysis);
        question.setPicture(picture);
        return question;
    }
}
