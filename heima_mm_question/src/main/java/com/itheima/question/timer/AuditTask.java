package com.itheima.question.timer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.common.constants.RedisPicConstants;
import com.itheima.question.mapper.QuestionItemMapper;
import com.itheima.question.mapper.QuestionMapper;
import com.itheima.question.pojo.Question;
import com.itheima.question.pojo.QuestionItem;
import com.itheima.question.util.AliyunGreenTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class AuditTask {
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    private AliyunGreenTemplate aliyunGreenTemplate;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    QuestionItemMapper questionItemMapper;

    @Scheduled(cron = "0 0 2 * * ? ")
    public void auditQuestion() throws Exception {
        Set<String> ids = redisTemplate.opsForSet().members(RedisPicConstants.audit_question);
        if (ids == null) {
            return;
        }
        for (String id : ids) {
            Question question = questionMapper.selectById(id);
            if (question == null) {
                redisTemplate.opsForSet().remove(RedisPicConstants.audit_question, id);
                continue;
            }
            QueryWrapper<QuestionItem> questionItemQueryWrapper = new QueryWrapper<>();
            questionItemQueryWrapper.eq("question_id", id);
            List<QuestionItem> questionItems = questionItemMapper.selectList(questionItemQueryWrapper);
            StringBuilder sb = new StringBuilder();
            sb.append(question.getSubject());
            List<String> pictureList = new ArrayList<>();
            pictureList.add(question.getPicture());
            if (questionItems != null && questionItems.size() > 0) {
                for (QuestionItem item : questionItems) {
                    sb.append(item.getContent());
                    pictureList.add(item.getPicture());
                }
            }
            Map<String, String> textScanResult = aliyunGreenTemplate.greenTextScan(sb.toString());
            Map<String, String> imageScanResult = aliyunGreenTemplate.imageScan(pictureList);
            if (!"pass".equals(textScanResult.get("suggestion"))) {
                question.setReviewStatus("2");
                questionMapper.updateById(question);
            }

            if (!"pass".equals(imageScanResult.get("suggestion"))) {
                question.setReviewStatus("2");
                questionMapper.updateById(question);
            }
            redisTemplate.opsForSet().remove(RedisPicConstants.audit_question, id);
        }
    }
}

