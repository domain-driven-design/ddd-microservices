package com.ddd.base.application.dto;

import com.example.common.domain.AbstractResponse;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class BranchResponse extends AbstractResponse {
    private String parentId;
    private String name;
    private String l1BranchId;
    private String parentName;
    private String pathName;
    private String level;
}
