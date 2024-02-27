package com.ddd.base.domain.aggregate;

import audit.AggregateAudit;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserIdentity extends AggregateAudit {
    private String permissionBranchId;
    private String userId;
    private List<UserIdentityRole> roles;
}
