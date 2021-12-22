package com.itheima.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.itheima.admin.PageVo;
import com.itheima.common.constants.RedisPicConstants;
import com.itheima.question.dto.QuestionDto;
import com.itheima.question.dto.QuestionPageDto;
import com.itheima.question.mapper.QuestionMapper;
import com.itheima.question.pojo.Question;
import com.itheima.question.service.QuestionService;
import com.itheima.question.vo.QuestionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Finger
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {
    @Resource
    QuestionMapper questionMapper;

    @Value("${fileServerUrl}")
    private String fileServerUrl;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean addQuestion(QuestionDto questionDto) {
        int id = questionMapper.insert(questionDto.toQuestion());
        boolean result = SqlHelper.retBool(id);
        if (result){
            redisTemplate.opsForSet().add(RedisPicConstants.audit_question, String.valueOf(id));
        }
        return result;
    }

    @Override
    public PageVo<QuestionVo> listQuestionVo(QuestionPageDto questionPageDto) {
        IPage<Question> page = new Page<>(questionPageDto.getCurrentPage(), questionPageDto.getPageSize());
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        if (questionPageDto.getCompanyName() != null) {
            queryWrapper.like("company_name", questionPageDto.getCompanyName());
        }
        IPage<Question> questionIPage = questionMapper.selectPage(page, queryWrapper);
        List<QuestionVo> list = questionIPage.getRecords().stream()
                .map(QuestionVo::fromQuestion)
                .collect(Collectors.toList());
        return new PageVo<>(list, (int) questionIPage.getTotal());
    }

}
