package com.itheima.question.service;

import com.itheima.question.util.AliyunGreenTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@EnableConfigurationProperties
@SpringBootTest
class QuestionServiceTest {

    @Autowired
    private AliyunGreenTemplate template;

    @Test
    public void test() throws Exception {

        Map<String, String> map1 = template.greenTextScan("本校小额贷款，安全、快捷、方便、无抵押，随机随贷，当天放款，上门服务");
        map1.forEach((k,v)-> System.out.println(k +"--" + v));
        List<String> list = new ArrayList<>();
        list.add("http://images.china.cn/site1000/2018-03/17/dfd4002e-f965-4e7c-9e04-6b72c601d952.jpg");
        Map<String, String> map2 = template.imageScan(list);
        System.out.println("------------");
        map2.forEach((k,v)-> System.out.println(k +"--" + v));
    }
}