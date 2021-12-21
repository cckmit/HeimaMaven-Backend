package com.itheima.question.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Finger
 */
@Configuration
@PropertySource("classpath:fast_dfs.properties")
@Import(FdfsClientConfig.class)
public class FdfsConfiguration {
}
