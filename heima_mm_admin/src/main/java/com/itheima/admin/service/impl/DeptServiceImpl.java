package com.itheima.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.IPage;
import com.itheima.admin.PageVo;
import com.itheima.admin.dto.DeptPageDto;
import com.itheima.admin.mapper.DeptMapper;
import com.itheima.admin.mapper.UserMapper;
import com.itheima.admin.pojo.Dept;
import com.itheima.admin.service.IDeptService;
import com.itheima.admin.vo.DeptVo;
import com.itheima.admin.vo.UserPageVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Resource
    DeptMapper deptMapper;

    @Override
    public List<DeptVo> listAllDeptVo() {
        List<Dept> deptList = list();
        return deptList.stream().map(dept->{
            DeptVo deptVo = new DeptVo();
            deptVo.setDeptId(dept.getDeptId());
            deptVo.setDeptName(dept.getDeptName());
            deptVo.setParentName(getById(dept.getDeptId()).getDeptName());
            deptVo.setState(dept.getState());
            return deptVo;
        }).collect(Collectors.toList());
    }

    @Override
    public PageVo<Object> queryByPage(DeptPageDto deptPageDto) {
        Page<Map<String, Object>> page = pageMaps(new Page<>(deptPageDto.getCurrentPage(),
                deptPageDto.getPageSize()),
                Wrappers.<Dept>query()
                        .eq("state", deptPageDto.getStatus())
                        .like("dept_name", deptPageDto.getDeptName()));
        return PageVo.builder()
                .rows(Collections.singletonList(page.getRecords()))
                .total((int) page.getTotal())
                .build();
    }
}
