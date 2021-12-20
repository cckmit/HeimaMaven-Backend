package com.itheima.springbootsecurity.config;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MyAuthticationSuccessHandle
 * @Description TODO
 * @Author lh
 * @Date 2021/12/20
 **/

public class MyAuthenticationFailureHandle implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        Map map = new HashMap<>();
        map.put("flag",false);
        map.put("msg","用户名或密码错误");
        String s = JSON.toJSONString(map);
        response.getWriter().write(s);
    }
}
