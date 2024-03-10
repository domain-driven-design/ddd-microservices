package com.ddd.base.domain.aggregate.user;

import audit.AggregateAudit;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@SuperBuilder
public class User extends AggregateAudit {
    private String name;
    private UserIdentity currentIdentity;
    private String abnormalBatch;
    private String status;
    private Boolean deleted;
    private String maintainBy;
    private String maintainByName;
    private OffsetDateTime maintainTime;
    private List<UserIdentity> userIdentity;

    public void buildUserIdentity(List<UserIdentity> identities, String currentIdentityId) {
        this.currentIdentity = identities.stream().filter(identity ->
                Objects.equals(identity.getId(), currentIdentityId)).findFirst().orElse(null);
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

    public void switchIdentity(String identityId) {
        if (!this.status.equals("NORMAL")) {
            throw new RuntimeException("USER STATUS INVALID");
        }
        this.currentIdentity = this.userIdentity.stream().filter(identity ->
                Objects.equals(identity.getId(), identityId)).findFirst().orElse(null);

        super.updatedAudit("");
    }
}
