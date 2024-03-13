package com.ddd.base.domain.aggregate.branch;

import domain.AbstractEntity;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Branch extends AbstractEntity {
    private String parentId;
    private String name;
    private String l1BranchId;
    private String parentName;
    private String pathName;
    private String level;

}
