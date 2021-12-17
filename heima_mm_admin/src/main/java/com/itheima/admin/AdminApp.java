package com.itheima.admin;

import com.heima.common.swagger.config.SwaggerConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@MapperScan("com.itheima.admin.mapper")
@SpringBootApplication
public class AdminApp {

    public static void main(String[] args) {
        SpringApplication.run(AdminApp.class, args);
    }

}
