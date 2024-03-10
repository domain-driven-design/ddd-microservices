package com.ddd.base.application.dto;

import com.ddd.base.domain.aggregate.user.User;
import com.ddd.base.domain.aggregate.user.UserIdentity;
import auth.UserIdentityRole;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
public class UserCreateCommand {
    private String id;
    private String name;
    private String permissionBranchId;
    private List<UserIdentityRole> roles;

    public User toEntity() {
        String identityId = UUID.randomUUID().toString();
        UserIdentity userIdentity = UserIdentity.builder()
                .id(identityId)
                .userId(this.id)
                .roles(roles)
                .permissionBranchId(permissionBranchId)
                .updatedBy("")
                .updatedTime(OffsetDateTime.now())
                .createdBy("")
                .createdTime(OffsetDateTime.now())
                .build();
        ArrayList<UserIdentity> userIdentities = new ArrayList<>();
        userIdentities.add(userIdentity);

        return User.builder()
                .id(this.id)
                .name(this.name)
                .maintainBy("")
                .maintainTime(OffsetDateTime.now())
                .deleted(false)
                .status("NORMAL")
                .currentIdentity(userIdentity)
                .userIdentity(userIdentities)
                .updatedBy("")
                .updatedTime(OffsetDateTime.now())
                .createdBy("")
                .createdTime(OffsetDateTime.now())
                .build();
    }
}

