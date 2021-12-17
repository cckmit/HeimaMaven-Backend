package com.itheima.admin.service;

import com.itheima.admin.PageVo;
import com.itheima.admin.dto.UserPageDto;
import com.itheima.admin.dto.UserRoleDto;
import com.itheima.admin.pojo.User;
import com.itheima.admin.vo.UserPageVo;
import com.itheima.admin.vo.UserVo;

/**
 * @author Finger
 */
public interface IUserService {
    UserVo queryById(String id);

    boolean deleteById(String id);

    boolean addUser(User user);

    PageVo<UserPageVo> queryByPage(UserPageDto userPageDto);

    boolean editUser(User user);

    boolean grantUserRole(UserRoleDto userRoleDto);
}
