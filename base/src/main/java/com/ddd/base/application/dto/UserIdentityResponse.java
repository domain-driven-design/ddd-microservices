package com.ddd.base.application.dto;

import auth.UserIdentityRole;
import lombok.Data;

import java.util.List;

@Data
public class UserIdentityResponse {

    private String id;
    private String permissionBranchId;
    private String userId;
    private List<UserIdentityRole> roles;
}
