package com.ddd.base.application.service.dto;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class UserResponse {
    private String name;
    private String currentIdentityId;
    private String status;
    private String maintainBy;
    private String maintainByName;
    private OffsetDateTime maintainTime;
    private List<UserIdentityResponse> userIdentities;
}
