package com.itheima.admin.controller;

import com.itheima.admin.PageVo;
import com.itheima.admin.Result;
import com.itheima.admin.dto.DeptPageDto;
import com.itheima.admin.pojo.Dept;
import com.itheima.admin.service.IDeptService;
import com.itheima.admin.vo.DeptOneVo;
import com.itheima.admin.vo.DeptVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
public class DepartmentController {
    @Resource
    IDeptService deptService;

    @GetMapping("/dept/listall")
    public List<DeptVo> listAllDepartment() {
        return deptService.listAllDeptVo();
    }

    @PostMapping("/dept/add")
    public Result<Object> addDepartment(@RequestBody Dept dept) {
        dept.setDeptId(UUID.randomUUID().toString());
        try {
            boolean result = deptService.save(dept);
            return Result.builder()
                    .flag(result)
                    .message(result ? "添加部门成功" : "添加部门失败")
                    .build();
        } catch (Exception e) {
            return Result.builder()
                    .flag(false)
                    .message("添加部门失败")
                    .build();
        }
    }

    @DeleteMapping("/dept/{id}")
    public Result<Object> deleteDepartment(@PathVariable String id){
        try {
            boolean result = deptService.removeById(id);
            return Result.builder()
                    .flag(result)
                    .message(result ? "删除部门成功" : "删除部门失败")
                    .build();
        } catch (Exception e) {
            return Result.builder()
                    .flag(false)
                    .message("删除部门失败")
                    .build();
        }
    }

    @PostMapping("/dept/list")
    public PageVo<Object> getDepartmentPage(@RequestBody DeptPageDto deptPageDto){
        if (deptPageDto == null) {
            return new PageVo<>(null, 0);
        }
        return deptService.queryByPage(deptPageDto);
    }

    @GetMapping("/dept/{id}")
    public DeptOneVo getDeptById(@PathVariable String id){
        Dept dept = deptService.getById(id);
        DeptOneVo deptOneVo = new DeptOneVo();
        BeanUtils.copyProperties(dept, deptOneVo);
        return deptOneVo;
    }

    @PutMapping("/dept/edit")
    public Result<Object> editDepartment(@RequestBody Dept dept){
        boolean result = deptService.updateById(dept);
        return Result.builder()
                .flag(result)
                .message(result ? "修改部门成功":"修改部门失败")
                .build();
    }

}
