package com.itheima.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Finger
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModuleAllItemVo {
    String id;
    String label;
    List<ModuleAllItemVo> children;
}
