package com.ddd.base.application.dto;

import com.example.common.auth.UserIdentityRole;
import com.example.common.domain.AbstractResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
public class UserIdentityResponse extends AbstractResponse {

    private String permissionBranchId;
    private String userId;
    private List<UserIdentityRole> roles;
}
