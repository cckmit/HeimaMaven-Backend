package com.itheima.springbootsecurity.config;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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

public class MyAuthenticationSuccessHandle implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        Map map = new HashMap<>();
        map.put("flag",true);
        map.put("msg","登录成功");
        String s = JSON.toJSONString(map);
        response.getWriter().write(s);
    }
}
