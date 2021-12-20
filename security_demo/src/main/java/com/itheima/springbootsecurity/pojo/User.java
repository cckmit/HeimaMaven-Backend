package com.itheima.springbootsecurity.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName User
 * @Description TODO
 * @Author lh
 * @Date 2021/12/20
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ss_user")
public class User {
    @TableId
    private String userId;
    private String email;
    private String userName;
    private String station;
    private String password;
    private String state;
    private String managerId;
    private String gender;
    private String telephone;
    private String birthday;
    private Integer degree;
    private Integer salary;
    private String joinDate;
    private Integer orderNo;
    private String remark;
    private String deptId;
    private String deptName;
    private String createBy;
    private String create_dept;
    private String updateBy;
}
