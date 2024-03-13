package com.ddd.base.application.dto;

import com.example.common.auth.UserContext;
import com.ddd.base.domain.aggregate.user.User;
import com.ddd.base.domain.aggregate.user.UserIdentity;
import com.example.common.auth.UserIdentityRole;
import lombok.Data;
import com.example.common.utils.IdUtil;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class UserCreateCommand {
    @NotBlank
    private String name;
    @NotBlank
    private String permissionBranchId;
    @NotEmpty
    private List<UserIdentityRole> roles;

    public User toEntity(UserContext userContext) {
        String identityId = IdUtil.uuid();
        String id = IdUtil.uuid();

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

