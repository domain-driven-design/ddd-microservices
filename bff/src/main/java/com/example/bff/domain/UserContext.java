package com.example.bff.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class UserContext {
    private String userId;
    private String userName;
    private ContextUserIdentity currentIdentity;

    @AllArgsConstructor
    @Builder
    @Data
    public static class ContextUserIdentity {
        private String permissionBranchId;
        private List<UserIdentityRole> roles;
    }

    @AllArgsConstructor
    @Getter
    public static enum UserIdentityRole {
        ADMIN("管理员"), USER("普通用户");
        private final String name;
    }
}
