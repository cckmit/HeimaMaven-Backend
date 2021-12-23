package com.itheima.common.safe;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Finger
 */
@Configuration
@Import(GreenProperties.class)
public class AliyunGreenConfiguration {

    @Bean
    @Autowired
    public AliyunGreenTemplate getAliyunGreenTemplate(GreenProperties greenProperties){
        return new AliyunGreenTemplate(greenProperties);
    }
}
