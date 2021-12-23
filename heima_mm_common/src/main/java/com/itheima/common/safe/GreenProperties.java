package com.itheima.common.safe;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.green")
public class GreenProperties {
    /**
     * 账号
     */
    public String accessKeyId;
    /**
     * 密钥
     */
    public String accessKeySecret;

    /**
     * 场景
     */
    public String scenes;
}
