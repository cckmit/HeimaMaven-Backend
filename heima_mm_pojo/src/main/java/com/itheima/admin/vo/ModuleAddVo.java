package com.itheima.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModuleAddVo {
    String name;
    String parentId;
    BigDecimal ctype;
    BigDecimal state;
    String curl;
    String remark;
}
