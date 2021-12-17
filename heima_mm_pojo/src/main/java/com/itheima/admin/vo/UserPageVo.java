package com.itheima.admin.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPageVo {
    private String userId;
    private String email;
    private String gender;
    private String userName;
    private String deptName;
    private Integer status;
}
