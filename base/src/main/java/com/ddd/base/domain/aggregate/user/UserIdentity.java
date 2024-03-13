package com.ddd.base.domain.aggregate.user;

import auth.UserIdentityRole;
import domain.AbstractEntity;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class UserIdentity extends AbstractEntity {
    private String permissionBranchId;
    private String userId;
    private List<UserIdentityRole> roles;
}
