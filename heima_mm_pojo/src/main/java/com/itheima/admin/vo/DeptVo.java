package com.itheima.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptVo {
    String deptId;
    String deptName;
    String parentName;
    String state;
}
