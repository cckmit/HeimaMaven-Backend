package com.itheima.admin.controller;

import com.itheima.admin.PageVo;
import com.itheima.admin.Result;
import com.itheima.admin.dto.UserPageDto;
import com.itheima.admin.dto.UserRoleDto;
import com.itheima.admin.pojo.User;
import com.itheima.admin.service.IUserService;
import com.itheima.admin.vo.UserPageVo;
import com.itheima.admin.vo.UserVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@RequestMapping
public class UserController {
    @Resource
    IUserService userService;

    @PostMapping("/user/{id}")
    public UserVo queryById(@PathVariable("id") String id) {
        return userService.queryById(id);
    }

    @DeleteMapping("/user/{id}")
    public Result<Object> deleteById(@PathVariable("id") String id) {
        boolean result = userService.deleteById(id);
        return Result.builder()
                .flag(result)
                .message(result ? "删除用户成功" : "删除用户失败")
                .build();
    }


    @PostMapping("/user/add")
    public Result<Object> addUser(@RequestBody User user) {
        user.setUserId(UUID.randomUUID().toString());
        boolean result = userService.addUser(user);
        return new Result<>(result, result ? "添加用户成功" : "添加用户失败", null);
    }

    @PostMapping("/user/list")
    public PageVo<UserPageVo> queryByPage(@RequestBody UserPageDto userPageDto) {
        if (userPageDto == null) {
            return new PageVo<>(null, 0);
        }
        return userService.queryByPage(userPageDto);
    }

    @PutMapping("/user/edit")
    public Result<Object> editUser(@RequestBody User user) {
        boolean result = userService.editUser(user);
        return new Result<>(result, result ? "修改用户成功" : "修改用户失败", null);
    }

    @PostMapping("/user/role")
    public Result<Object> grantUserRole(@RequestBody UserRoleDto userRoleDto) {
        boolean result = userService.grantUserRole(userRoleDto);
        return new Result<>(result, result ? "用户授权成功" : "用户授权失败", null);
    }
}
