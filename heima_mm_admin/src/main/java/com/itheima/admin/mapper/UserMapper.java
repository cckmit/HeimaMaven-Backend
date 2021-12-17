package com.itheima.admin.mapper;

import com.itheima.admin.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("SELECT * FROM ss_user WHERE user_id = #{id}")
    User selectById(String id);

    @Delete("DELETE FROM ss_user WHERE user_id = #{id}")
    boolean deleteById(String id);

    @Insert("INSERT INTO ss_user(user_id, email, password,user_name,dept_id,gender,state,birthday,join_date,telephone) VALUES " +
            "(#{userId}, #{email}, #{password}, #{userName}, #{deptId}, #{gender}, #{state}, " +
            "#{birthday}, #{joinDate}, #{telephone})")
    boolean addUser(User user);

    List<User> selectByPage(@Param("status") int status,@Param("username") String username);

    boolean updataUser(User user);
}
