package com.itheima.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.admin.PageVo;
import com.itheima.admin.dto.RoleListDto;
import com.itheima.admin.mapper.DeptMapper;
import com.itheima.admin.mapper.RoleMapper;
import com.itheima.admin.pojo.Dept;
import com.itheima.admin.pojo.Role;
import com.itheima.admin.service.IRoleService;
import com.itheima.admin.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageVo<RoleVo> queryByPage(RoleListDto pageDto){
        IPage<Role> page = new Page<>(pageDto.getCurrentPage(),pageDto.getPageSize());
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",pageDto.getName());
        IPage<Role> roleIPage = roleMapper.selectPage(page,queryWrapper);
        List<RoleVo> list =  roleIPage.getRecords().stream().map(role -> {
                    RoleVo roleVo = new RoleVo();
                    roleVo.setName(role.getName());
                    roleVo.setRoleId(role.getRoleId());
                    roleVo.setRemark(role.getRemark());
                    roleVo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(role.getCreateTime()));
                    return roleVo;
                }
        ).collect(Collectors.toList());
        return new PageVo<>(list, (int) roleIPage.getTotal());
    }

    @Override
    public List<Integer> queryModulesByRoleId(String id) {
        return roleMapper.queryModulesByRoleId(id);
    }

    @Override
    public boolean authRole(String roleId, List<String> modulesIds) {
        return roleMapper.addRoleToModule(roleId, modulesIds);
    }
}
