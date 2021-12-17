package com.itheima.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo {
    private String roleId;
    private String name;
    private String remark;
    private String createTime;
}
