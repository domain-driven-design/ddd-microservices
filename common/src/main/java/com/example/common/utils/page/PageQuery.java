package com.example.common.utils.page;

import lombok.Data;

@Data
public class PageQuery {
    private long PageNumber = 1;
    private long pageSize = 10;
}
