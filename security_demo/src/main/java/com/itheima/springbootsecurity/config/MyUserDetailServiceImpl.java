package com.itheima.springbootsecurity.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.springbootsecurity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName MyUserDetailServiceImpl
 * @Description 作用：根据用户名加载用户信息
 * @Author lh
 * @Date 2021/12/20
 **/
@Component
public class MyUserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private UserMapper userMapper;

    /**
     * @param username 用户名
     * @return userDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       com.itheima.springbootsecurity.pojo.User mysqlUser = userMapper.selectOne(new QueryWrapper<com.itheima.springbootsecurity.pojo.User>().eq("user_name", username));
        //写数据库访问的代码
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        List<String> list = userMapper.selectRoles(username);
        if(list!=null && list.size()>0) {
            for (String role: list) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }
        if(mysqlUser == null) {
            return null;
        }
        return new User(mysqlUser.getUserName(),mysqlUser.getPassword(),authorities);
    }
}
