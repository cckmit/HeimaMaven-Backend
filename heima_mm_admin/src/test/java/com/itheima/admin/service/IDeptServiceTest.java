package com.itheima.admin.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IDeptServiceTest {

    @Resource
    IDeptService deptService;

    @Test
    void listAllDeptVo() {
        System.out.println(deptService.listAllDeptVo());
    }
}