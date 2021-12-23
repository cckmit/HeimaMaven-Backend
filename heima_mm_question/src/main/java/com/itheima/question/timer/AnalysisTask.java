package com.itheima.question.timer;

import com.itheima.common.constants.RedisPicConstants;
import com.itheima.question.config.FastDFSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AnalysisTask {

    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    FastDFSClientUtil fastDFSClientUtil;

    @Scheduled(cron = "0/5 * * * * ? ")
    public void clearPic() {
        Set<String> pics = redisTemplate.opsForSet()
                .difference(RedisPicConstants.ALL_PIC, RedisPicConstants.USE_PIC);
        for (String pic : pics) {
            fastDFSClientUtil.delFile(pic);
        }
        redisTemplate.delete(RedisPicConstants.ALL_PIC);
        redisTemplate.delete(RedisPicConstants.USE_PIC);
    }
}
