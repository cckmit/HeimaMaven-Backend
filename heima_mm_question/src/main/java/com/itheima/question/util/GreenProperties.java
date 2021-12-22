package com.itheima.question.util;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class GreenProperties {
    /**
     * 账号
     */
    String accessKeyID;
    /**
     * 密钥
     */
    String accessKeySecret;

    /**
     * 场景
     */
    String scenes;
}
