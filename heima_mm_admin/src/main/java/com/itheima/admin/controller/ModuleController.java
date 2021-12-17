package com.itheima.admin.controller;

import com.itheima.admin.PageVo;
import com.itheima.admin.Result;
import com.itheima.admin.dto.ModulePageDto;
import com.itheima.admin.pojo.Module;
import com.itheima.admin.service.IModuleService;
import com.itheima.admin.vo.ModuleAddVo;
import com.itheima.admin.vo.ModuleAllItemVo;
import com.itheima.admin.vo.ModuleQueryVo;
import com.itheima.admin.vo.ModuleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/module")
public class ModuleController {

    @Resource
    IModuleService moduleService;

    @PostMapping("/list")
    public PageVo<ModuleVo> listModuleVoPage(@RequestBody ModulePageDto modulePageDto){
        return moduleService.listModuleVoPage(modulePageDto);
    }

    @GetMapping("/listall")
    public List<ModuleAllItemVo> listAllModule(){
        return moduleService.listAllModule();
    }

    @PostMapping("/add")
    public Result<Object> addModule(@RequestBody ModuleAddVo moduleAddVo){
        Module module = new Module();
        if (StringUtils.isNotEmpty(moduleAddVo.getParentId())){
            module.setParentName(moduleService.getById(module.getModuleId()).getName());
        }
        BeanUtils.copyProperties(moduleAddVo, module);
        boolean result = moduleService.save(module);
        return Result.builder()
                .flag(result)
                .message(result ? "添加模块成功":"添加模块失败")
                .build();
    }

    @DeleteMapping("/{id}")
    public Result<Object> deleteModule(@PathVariable("id") String id){
        boolean result = moduleService.removeById(id);
        return Result.builder()
                .flag(result)
                .message(result ? "删除模块成功":"删除模块失败")
                .build();
    }

    @GetMapping("/{id}")
    public ModuleQueryVo getModuleQueryById(@PathVariable("id") String id){
        ModuleQueryVo moduleQueryVo = new ModuleQueryVo();
        BeanUtils.copyProperties(moduleService.getById(id), moduleQueryVo);
        return moduleQueryVo;
    }

    @PutMapping("/edit")
    public Result<Object> editModule(@RequestBody ModuleQueryVo moduleQueryVo){
        Module module = new Module();
        BeanUtils.copyProperties(moduleQueryVo, module);
        boolean result = moduleService.updateById(module);
        return Result.builder()
                .flag(result)
                .message(result ? "修改模块成功":"修改模块失败")
                .build();
    }
}
