package com.itheima.question.util;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

public class TanhuaAutoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "tanhua.green",value = "enable", havingValue = "true")
    public AliyunGreenTemplate aliyunGreenTemplate(GreenProperties properties) {
        return new AliyunGreenTemplate(properties);
    }
}
