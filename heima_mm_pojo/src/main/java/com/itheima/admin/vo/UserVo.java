package com.itheima.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private String userId;
    private String email;
    private String password;
    private String userName;
    private String deptId;
    //0女  1男
    private String gender;
    //1启用  0禁用
    private String state;
    private String birthday;
    private String joinDate;
    private String telephone;
}
