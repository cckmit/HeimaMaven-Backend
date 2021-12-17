package com.itheima.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "ss_module")
public class Module {
    @TableId(value = "module_id", type = IdType.ASSIGN_UUID)
    String moduleId;
    String parentId;
    String parentName;
    String name;
    BigDecimal isLeaf;
    String ico;
    String cpermission;
    String curl;
    BigDecimal ctype;
    BigDecimal state;
    String belong;
    String remark;
    BigDecimal orderNo;
}

