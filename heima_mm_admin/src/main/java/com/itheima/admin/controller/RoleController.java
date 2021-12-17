package com.itheima.admin.controller;

import com.itheima.admin.PageVo;
import com.itheima.admin.Result;
import com.itheima.admin.dto.RoleAddDto;
import com.itheima.admin.dto.RoleAuthDto;
import com.itheima.admin.dto.RoleListDto;
import com.itheima.admin.pojo.Role;
import com.itheima.admin.service.IRoleService;
import com.itheima.admin.vo.RoleOneVo;
import com.itheima.admin.vo.RoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @PostMapping("/list")
    public PageVo<RoleVo> queryByPage(@RequestBody RoleListDto pageDto){
        return roleService.queryByPage(pageDto);
    }

    @GetMapping("/listall")
    public List<RoleVo> listAllRole(){
        return roleService.list().stream().map(role->{
            RoleVo roleVo = new RoleVo();
            BeanUtils.copyProperties(role, roleVo);
            return roleVo;
        }).collect(Collectors.toList());
    }

    @PostMapping("/role/add")
    public Result<Object> addRole(@RequestBody RoleAddDto roleAddDto){
        Role role = new Role();
        BeanUtils.copyProperties(roleAddDto, role);
        boolean result = roleService.save(role);
        return Result.builder()
                .flag(result)
                .message(result ? "添加角色成功" : "添加角色失败")
                .build();
    }

    @DeleteMapping("/role/{id}")
    public Result<Object> deleteByUserId(@PathVariable("id") String id) {
        boolean result = roleService.removeById(id);
        return Result.builder()
                .flag(result)
                .message(result ? "删除角色成功" : "删除角色失败")
                .build();
    }

    @GetMapping("/role/{id}")
    public RoleOneVo getRoleById(@PathVariable("id") String id){
        Role role = roleService.getById(id);
        RoleOneVo roleOneVo = new RoleOneVo();
        BeanUtils.copyProperties(role, roleOneVo);
        return roleOneVo;
    }

    @PutMapping("/role/edit")
    public Result<Object> editRole(@RequestBody RoleOneVo roleOneVo){
        Role role = new Role();
        BeanUtils.copyProperties(roleOneVo, role);
        boolean result = roleService.updateById(role);
        return Result.builder()
                .flag(result)
                .message(result ? "修改角色成功" : "修改角色失败")
                .build();
    }

    @GetMapping("/role/{id}/modules")
    public Result<Object> getModulesByRoleId(String id){
        List<Integer> list = roleService.queryModulesByRoleId(id);
        return Result.builder()
                .flag(true)
                .data(list)
                .message("查询模块成功")
                .build();
    }

    @PostMapping("/role/auth")
    public Result<Object> authRole(@RequestBody RoleAuthDto roleAuthDto){
        boolean result = roleService.authRole(roleAuthDto.getRoleId(), roleAuthDto.getModulesIds());
        return Result.builder()
                .flag(result)
                .message(result?"角色授权成功":"角色授权失败")
                .build();
    }
}
