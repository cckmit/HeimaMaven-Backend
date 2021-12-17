package com.itheima.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto {
    int currentPage;
    int pageSize;
    String queryString;
    int status;
}

