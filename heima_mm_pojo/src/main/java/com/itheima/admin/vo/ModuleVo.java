package com.itheima.admin.vo;

import com.itheima.admin.pojo.Module;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ModuleVo {
    String moduleId;
    String name;
    String ctype;
    String parentName;
    String curl;
    String state;

    public static ModuleVo fromModule(Module module){
        ModuleVo moduleVo = new ModuleVo();
        switch (module.getCtype().intValue()){
            case 0:
                moduleVo.setCtype("主菜单");
                break;
            case 1:
                moduleVo.setCtype("左侧菜单");
                break;
            case 2:
                moduleVo.setCtype("按钮");
                break;
            case 3:
                moduleVo.setCtype("链接");
                break;
            case 4:
                moduleVo.setCtype("状态");
                break;
        }
        if (module.getState().intValue() == 0){
            moduleVo.setState("停用");
        }else{
            moduleVo.setState("启用");
        }
        moduleVo.setModuleId(module.getModuleId());
        moduleVo.setName(module.getName());
        moduleVo.setCurl(module.getCurl());
        moduleVo.setParentName(module.getParentName());
        return moduleVo;
    }
}
