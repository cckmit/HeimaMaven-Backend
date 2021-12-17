package com.itheima.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.admin.PageVo;
import com.itheima.admin.dto.DeptPageDto;
import com.itheima.admin.mapper.DeptMapper;
import com.itheima.admin.pojo.Dept;
import com.itheima.admin.service.IDeptService;
import com.itheima.admin.vo.DeptVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Resource
    DeptMapper deptMapper;

    @Override
    public List<DeptVo> listAllDeptVo() {
        List<Dept> deptList = list();
        return deptList.stream().map(dept -> {
            DeptVo deptVo = new DeptVo();
            deptVo.setDeptId(dept.getDeptId());
            deptVo.setDeptName(dept.getDeptName());
            deptVo.setParentName(getById(dept.getDeptId()).getDeptName());
            deptVo.setState(dept.getState());
            return deptVo;
        }).collect(Collectors.toList());
    }

    @Override
    public PageVo<DeptVo> queryByPage(DeptPageDto deptPageDto) {
        IPage<Dept> page = new Page<>(deptPageDto.getCurrentPage(), deptPageDto.getPageSize());
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        if (deptPageDto.getDeptName() != null) {
            queryWrapper.like("dept_name", deptPageDto.getDeptName());
        }
        queryWrapper.eq("state", new BigDecimal(deptPageDto.getStatus()));
        IPage<Dept> deptIPage = deptMapper.selectPage(page, queryWrapper);
        List<DeptVo> list = deptIPage.getRecords().stream().map(dept -> {
                    DeptVo deptVo = new DeptVo();
                    deptVo.setDeptId(dept.getDeptId());
                    deptVo.setDeptName(dept.getDeptName());
                    deptVo.setParentName(dept.getParentId());
                    deptVo.setState(dept.getState());
                    return deptVo;
                }
        ).collect(Collectors.toList());
        return new PageVo<DeptVo>(list, (int) deptIPage.getTotal());
    }
}
