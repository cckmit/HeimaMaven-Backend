package com.itheima.admin.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Finger
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userId;
    private String email;
    private String userName;
    private String station;
    private String password;
    private BigDecimal state;
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
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    private String updateBy;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
}
