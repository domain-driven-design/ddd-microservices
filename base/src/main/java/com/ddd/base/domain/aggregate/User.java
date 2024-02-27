package com.ddd.base.domain.aggregate;

import audit.Audit;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User extends Audit {
    private String name;
    private String currentIdentityId;
    private String abnormalBatch;
    private String status;
    private Boolean deleted;
    private String maintainBy;
    private String maintainByName;
    private String maintainTime;
    private String version;
    private UserIdentity userIdentity;
}
