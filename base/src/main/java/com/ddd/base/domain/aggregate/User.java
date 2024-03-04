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

    public void buildUserIdentity(List<UserIdentity> identities) {
        this.userIdentity = identities;
    }

    public void disable(String id) {
        if (!this.status.equals("NORMAL")) {
            throw new RuntimeException("USER STATUS INVALID");
        }
        this.status = "DISABLE";
        this.maintainBy = id;
        this.maintainTime = OffsetDateTime.now();
        super.updatedAudit(id);

    }

    public void enable(String id) {
        if (!this.status.equals("DISABLE")) {
            throw new RuntimeException("USER STATUS INVALID");
        }
        this.status = "NORMAL";
        this.maintainBy = id;
        this.maintainTime = OffsetDateTime.now();
        super.updatedAudit(id);
    }
}
