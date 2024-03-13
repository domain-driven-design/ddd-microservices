package com.ddd.base.application.dto;

import auth.UserIdentityRole;
import domain.AbstractResponse;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class UserIdentityResponse extends AbstractResponse {

    private String permissionBranchId;
    private String userId;
    private List<UserIdentityRole> roles;
}
