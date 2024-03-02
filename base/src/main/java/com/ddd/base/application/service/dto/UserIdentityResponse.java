package com.ddd.base.application.service.dto;

import com.ddd.base.domain.aggregate.UserIdentityRole;
import lombok.Data;

import java.util.List;

@Data
public class UserIdentityResponse {

    private String permissionBranchId;
    private String userId;
    private List<UserIdentityRole> roles;
}
