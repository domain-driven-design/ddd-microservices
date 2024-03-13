package com.example.bff.infrastructure.api.dto;

import com.example.common.auth.UserIdentityRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserIdentityResult {
    private String permissionBranchId;
    private String userId;
    private List<UserIdentityRole> roles;
}
