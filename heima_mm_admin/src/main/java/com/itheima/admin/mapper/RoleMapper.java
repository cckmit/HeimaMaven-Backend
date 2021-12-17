package com.itheima.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.admin.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    @Select("SELECT module_id FROM ss_role_module WHERE role_id = #{id}")
    List<Integer> queryModulesByRoleId(String id);

    boolean addRoleToModule(@Param("roleId") String roleId, @Param("moduleIds")List<String> modulesIds);
}
