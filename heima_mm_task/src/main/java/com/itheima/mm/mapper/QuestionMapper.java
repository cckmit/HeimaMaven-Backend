package com.itheima.mm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.question.pojo.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
}
