package com.itheima.admin.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

public interface UserRoleMapper {

    @Insert("INSERT INTO ss_role_user (user_id, role_id) VALUES (#{userId}, #{roleId}) " +
            "ON DUPLICATE KEY UPDATE role_id=role_id;")
    boolean addRole(String userId, String roleId);
}
