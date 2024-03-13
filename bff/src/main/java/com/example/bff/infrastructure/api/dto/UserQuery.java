package com.example.bff.infrastructure.api.dto;

import com.example.common.auth.UserIdentityRole;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class UserQuery {
    private String id;
    private String name;
    private String status;
    private String maintainBy;
    private String maintainByName;
    private String permissionBranchId;
    private UserIdentityRole role;
}

