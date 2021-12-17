package com.itheima.admin.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class IUserServiceTest {

    @Resource
    IUserService userService;

    @Test
    void queryById() {
        System.out.println(userService.queryById(""));
    }
}