package com.itheima.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.admin.PageVo;
import com.itheima.admin.dto.ModulePageDto;
import com.itheima.admin.mapper.ModuleMapper;
import com.itheima.admin.pojo.Module;
import com.itheima.admin.service.IModuleService;
import com.itheima.admin.vo.ModuleAllItemVo;
import com.itheima.admin.vo.ModuleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, Module> implements IModuleService {
    @Autowired
    ModuleMapper moduleMapper;

    @Override
    public PageVo<ModuleVo> listModuleVoPage(ModulePageDto modulePageDto) {
        IPage<Module> page = new Page<>(modulePageDto.getCurrentPage(), modulePageDto.getPageSize());
        QueryWrapper<Module> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", modulePageDto.getName());
        IPage<Module> moduleIPage = moduleMapper.selectPage(page, queryWrapper);
        List<ModuleVo> list = moduleIPage.getRecords().stream().map(ModuleVo::fromModule
        ).collect(Collectors.toList());
        return new PageVo<>(list, (int) moduleIPage.getTotal());
    }

    @Override
    public List<ModuleAllItemVo> listAllModule() {
        List<Module> moduleList = list(new QueryWrapper<Module>().isNull("parent_id" ));
        List<ModuleAllItemVo> list = moduleList.stream().map(module -> {
            ModuleAllItemVo moduleAllItemVo = new ModuleAllItemVo();
            moduleAllItemVo.setId(module.getModuleId());
            moduleAllItemVo.setLabel(module.getName());
            return moduleAllItemVo;
        }).collect(Collectors.toList());
        for (ModuleAllItemVo module: list){
            module.setChildren(listModules(String.valueOf(module.getId())));
        }
        return list;
    }

    List<ModuleAllItemVo> listModules(String parentId){
        List<Module> list = list(new QueryWrapper<Module>().eq("parent_id", parentId));
        return list.stream().map(module -> {
            ModuleAllItemVo moduleAllItemVo = new ModuleAllItemVo();
            moduleAllItemVo.setId(module.getModuleId());
            moduleAllItemVo.setLabel(module.getName());
            if (module.getParentId()!=null){
                moduleAllItemVo.setChildren(listModules(module.getModuleId()));
            }
            return moduleAllItemVo;
        }).collect(Collectors.toList());
    }
}
