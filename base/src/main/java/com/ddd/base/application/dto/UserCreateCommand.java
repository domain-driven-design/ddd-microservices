package com.ddd.base.application.dto;

import auth.UserContext;
import com.ddd.base.domain.aggregate.user.User;
import com.ddd.base.domain.aggregate.user.UserIdentity;
import auth.UserIdentityRole;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
public class UserCreateCommand {
    @NotBlank
    private String name;
    @NotBlank
    private String permissionBranchId;
    @NotEmpty
    private List<UserIdentityRole> roles;

    public User toEntity(UserContext userContext) {
        String identityId = UUID.randomUUID().toString();
        String id = UUID.randomUUID().toString();

        UserIdentity userIdentity = UserIdentity.builder()
                .id(identityId)
                .userId(id)
                .roles(roles)
                .permissionBranchId(permissionBranchId)
                .updatedBy(userContext.getUserId())
                .updatedTime(OffsetDateTime.now())
                .createdBy(userContext.getUserId())
                .createdTime(OffsetDateTime.now())
                .build();
        ArrayList<UserIdentity> userIdentities = new ArrayList<>();
        userIdentities.add(userIdentity);

        return User.builder()
                .id(id)
                .name(this.name)
                .maintainBy(userContext.getUserId())
                .maintainByName(userContext.getUserName())
                .maintainTime(OffsetDateTime.now())
                .deleted(false)
                .status("NORMAL")
                .currentIdentity(userIdentity)
                .userIdentities(userIdentities)
                .updatedBy(userContext.getUserId())
                .updatedTime(OffsetDateTime.now())
                .createdBy(userContext.getUserId())
                .createdTime(OffsetDateTime.now())
                .build();
    }
}

