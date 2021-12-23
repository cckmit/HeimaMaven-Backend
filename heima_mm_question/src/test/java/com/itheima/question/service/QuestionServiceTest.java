package com.itheima.question.service;

import com.itheima.question.util.AliyunGreenTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class QuestionServiceTest {

    @Autowired
    private AliyunGreenTemplate template;

    @Test
    public void test() throws Exception {
/*        Long data = analysisMapper.sumAnalysisData("num_registered", "2020-09-14", "2020-09-18");
        System.out.println(data);
        Map<String, String> map = template.greenTextScan("本校小额贷款，安全、快捷、方便、无抵押，随机随贷，当天放款，上门服务");
        map.forEach((k,v)-> System.out.println(k +"--" + v));*/
        List<String> list = new ArrayList<>();
        list.add("http://images.china.cn/site1000/2018-03/17/dfd4002e-f965-4e7c-9e04-6b72c601d952.jpg");
        Map<String, String> map = template.imageScan(list);
        System.out.println("------------");
        map.forEach((k,v)-> System.out.println(k +"--" + v));
    }
}