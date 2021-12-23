package com.itheima.question.config;

import com.itheima.common.constants.RedisPicConstants;
import com.itheima.question.mapper.QuestionMapper;
import com.itheima.question.pojo.Question;
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
public class AnalysisTask {

    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    FastDFSClientUtil fastDFSClientUtil;
    @Autowired
    private AliyunGreenTemplate aliyunGreenTemplate;
    @Autowired
    QuestionMapper questionMapper;

    @Scheduled( cron = "0/5 * * * * ? ")
    public void clearPic() {
        Set<String> pics = redisTemplate.opsForSet()
                .difference(RedisPicConstants.ALL_PIC, RedisPicConstants.USE_PIC);
        for (String pic : pics){
            fastDFSClientUtil.delFile(pic);
        }
        redisTemplate.delete(RedisPicConstants.ALL_PIC);
        redisTemplate.delete(RedisPicConstants.USE_PIC);
    }

    @Scheduled( cron = "0/5 * * * * ? ")
    public void clearBadPic() throws Exception {
        Set<String> ids = redisTemplate.opsForSet().members(RedisPicConstants.audit_question);
        for (String id : ids){
            Question question = questionMapper.selectById(id);
            List<String> list = new ArrayList<>();
            list.add(question.getPicture());
            Map<String, String> map = aliyunGreenTemplate.imageScan(list);
            if (!"pass".equals(map.get("suggestion"))){
                question.setReviewStatus("2");
                questionMapper.updateById(question);
            }
            redisTemplate.opsForSet().remove(RedisPicConstants.audit_question, id);
        }
    }

    @Scheduled( cron = "0/5 * * * * ? ")
    public void clearBadText() throws Exception {
        Set<String> ids = redisTemplate.opsForSet().members(RedisPicConstants.audit_question);
        for (String id : ids){
            Question question = questionMapper.selectById(id);
            Map<String, String> map = aliyunGreenTemplate.greenTextScan(question.getSubject());
            if (!"pass".equals(map.get("suggestion"))){
                question.setReviewStatus("2");
                questionMapper.updateById(question);
            }
            redisTemplate.opsForSet().remove(RedisPicConstants.audit_question, id);
        }
    }
}
