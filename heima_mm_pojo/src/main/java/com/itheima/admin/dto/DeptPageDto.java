package com.itheima.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptPageDto {
    int currentPage;
    int pageSize;
    String deptName;
    int status;
}
