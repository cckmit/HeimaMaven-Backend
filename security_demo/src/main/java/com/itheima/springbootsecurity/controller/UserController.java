package com.itheima.springbootsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author lh
 * @Date 2021/12/20
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/name")
    @PreAuthorize("hasRole('ADMIN')")
    public String getUser(HttpSession session){
        session.setAttribute("username","zhangsan");
        return "zhangsan";
    }
}
