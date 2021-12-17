package com.itheima.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.admin.PageVo;
import com.itheima.admin.dto.RoleListDto;
import com.itheima.admin.pojo.Dept;
import com.itheima.admin.pojo.Role;
import com.itheima.admin.vo.RoleVo;

import java.util.List;

public interface IRoleService extends IService<Role> {
    PageVo<RoleVo> queryByPage(RoleListDto pageDto);

    List<Integer> queryModulesByRoleId(String id);

    boolean authRole(String roleId, List<String> modulesIds);
}
