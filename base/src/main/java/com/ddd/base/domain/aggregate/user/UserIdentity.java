package com.ddd.base.domain.aggregate.user;

import audit.AggregateAudit;
import auth.UserIdentityRole;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class UserIdentity extends AggregateAudit {
    private String permissionBranchId;
    private String userId;
    private List<UserIdentityRole> roles;
}
