package com.ddd.base.domain.aggregate.branch;

import audit.AggregateAudit;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Branch extends AggregateAudit {
    private String parentId;
    private String name;
    private String l1BranchId;
    private String parentName;
    private String pathName;
    private String level;

}
