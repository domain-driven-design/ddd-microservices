package com.ddd.base.domain.aggregate;

import audit.Audit;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserIdentity extends Audit {
    private String permissionBranchId;
    private String userId;
    private List<UserIdentityRole> roles;
}
