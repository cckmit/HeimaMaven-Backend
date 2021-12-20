package com.itheima.springbootsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.springbootsecurity.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author lh
 * @Date 2021/12/20
 **/

public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT\n" +
            "\ts1.role_desc\n" +
            "FROM\n" +
            "\tss_role s1,\n" +
            "\tss_user s2,\n" +
            "\tss_role_user s3 \n" +
            "WHERE\n" +
            "\ts1.role_id = s3.role_id \n" +
            "\tAND s2.user_id = s3.user_id\n" +
            "\tand s2.user_name=#{username}")
    List<String> selectRoles(String username);

}
