package com.ddd.base.application.dto;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class UserResponse {
    private String id;
    private String name;
    private UserIdentityResponse currentIdentity;
    private String status;
    private String maintainBy;
    private String maintainByName;
    private OffsetDateTime maintainTime;
    private List<UserIdentityResponse> userIdentities;
}
