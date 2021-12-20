package com.itheima.springbootsecurity.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping({"/", "/index"})
    public String hello() {
        return "hello";
    }

    @GetMapping("/admin/hello")
    public String admin() {
        return "hello,admin";
    }

    @GetMapping("user/hello")
    public String user() {
        return "hello,user";
    }

    @GetMapping("dba/hello")
    public String dba() {
        return "hello,dba";
    }

}
