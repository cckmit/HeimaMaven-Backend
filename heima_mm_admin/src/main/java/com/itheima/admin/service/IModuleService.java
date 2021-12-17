package com.itheima.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.admin.PageVo;
import com.itheima.admin.dto.ModulePageDto;
import com.itheima.admin.pojo.Module;
import com.itheima.admin.vo.ModuleAllItemVo;
import com.itheima.admin.vo.ModuleVo;

import java.util.List;

public interface IModuleService extends IService<Module> {
    PageVo<ModuleVo> listModuleVoPage(ModulePageDto modulePageDto);

    List<ModuleAllItemVo> listAllModule();
}
