package com.itheima.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.admin.PageVo;
import com.itheima.admin.dto.DeptPageDto;
import com.itheima.admin.pojo.Dept;
import com.itheima.admin.vo.DeptVo;

import java.util.List;

public interface IDeptService extends IService<Dept> {
    List<DeptVo> listAllDeptVo();

    PageVo<Object> queryByPage(DeptPageDto deptPageDto);
}
