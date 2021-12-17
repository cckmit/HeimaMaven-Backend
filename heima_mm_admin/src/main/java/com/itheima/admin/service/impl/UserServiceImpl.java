package com.itheima.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.admin.PageVo;
import com.itheima.admin.dto.UserPageDto;
import com.itheima.admin.dto.UserRoleDto;
import com.itheima.admin.mapper.UserMapper;
import com.itheima.admin.mapper.UserRoleMapper;
import com.itheima.admin.pojo.User;
import com.itheima.admin.service.IUserService;
import com.itheima.admin.vo.UserPageVo;
import com.itheima.admin.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Finger
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    UserMapper userMapper;
    @Resource
    UserRoleMapper userRoleMapper;

    @Override
    public UserVo queryById(String id) {
        User user = userMapper.selectById(id);
        UserVo userVo = new UserVo();
        if (user != null) {
            BeanUtils.copyProperties(user, userVo);
        }
        return userVo;
    }

    @Override
    public boolean deleteById(String id) {
        return userMapper.deleteById(id);
    }

    @Override
    public boolean addUser(User user) {
        try{
            return userMapper.addUser(user);
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public PageVo<UserPageVo> queryByPage(UserPageDto userPageDto) {
        PageHelper.startPage(userPageDto.getCurrentPage(), userPageDto.getPageSize());
        List<User> pages =  userMapper.selectByPage(userPageDto.getStatus(), userPageDto.getUsername());
        PageInfo<User> page = new PageInfo<>(pages);
        List<UserPageVo> list = page.getList().stream().map(user->{
            UserPageVo userPageVo = new UserPageVo();
            BeanUtils.copyProperties(user,userPageVo);
            userPageVo.setStatus(user.getState());
            return userPageVo;
        }).collect(Collectors.toList());
        return new PageVo<UserPageVo>(list, (int) page.getTotal());
    }

    @Override
    public boolean editUser(User user) {
        return userMapper.updataUser(user);
    }

    @Override
    public boolean grantUserRole(UserRoleDto userRoleDto) {
        boolean result = true;
        //for each
        for (String roleId:userRoleDto.getRoleIds()){
            boolean tmp= userRoleMapper.addRole(userRoleDto.getUserId(), roleId);
            if (!tmp){
                result = false;
            }
        }
        return result;
    }
}
