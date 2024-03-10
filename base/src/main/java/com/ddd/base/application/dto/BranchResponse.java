package com.ddd.base.application.dto;

import lombok.Data;

@Data
public class BranchResponse {
    private String id;
    private String parentId;
    private String name;
    private String l1BranchId;
    private String parentName;
    private String pathName;
    private String level;
}
