package com.itheima.common.fastdfs;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public FastDFSClientUtil getFdfsClientUtil(){
        return new FastDFSClientUtil();
    }
}
