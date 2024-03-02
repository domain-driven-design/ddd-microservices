package com.ddd.base.domain.aggregate;

import audit.AggregateAudit;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@SuperBuilder
public class User extends AggregateAudit {
    private String name;
    private String currentIdentityId;
    private String abnormalBatch;
    private String status;
    private Boolean deleted;
    private String maintainBy;
    private String maintainByName;
    private OffsetDateTime maintainTime;
    private List<UserIdentity> userIdentity;
}
