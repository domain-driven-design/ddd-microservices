package com.ddd.base.infra.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@TableName("branch")
public class BranchPO {

    private String parentId;
    private String name;
    private String l1BranchId;
    private String parentName;
    private String pathName;
    private String level;

    private String id;
    private String createdBy;
    private OffsetDateTime createdTime;
    private String updatedBy;
    private OffsetDateTime updatedTime;
}
