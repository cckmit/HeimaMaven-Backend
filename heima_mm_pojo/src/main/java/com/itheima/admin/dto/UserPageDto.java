package com.itheima.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPageDto {
    int currentPage;
    int pageSize;
    String username;
    int status;
}
