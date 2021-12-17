package com.itheima.admin.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ss_role")
public class Role {
    @TableId(value = "role_id", type = IdType.ASSIGN_UUID)
    private String roleId;
    private String name;
    private String remark;
    private Integer orderNo;
    private String createBy;
    private String createDept;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    private String updateBy;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

}