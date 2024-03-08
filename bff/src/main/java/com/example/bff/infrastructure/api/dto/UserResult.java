package com.example.bff.infrastructure.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class UserResult {
    private String id;
    private String name;
    private String currentIdentityId;
    private String status;
    private String maintainBy;
    private String maintainByName;
    private OffsetDateTime maintainTime;
    private List<UserIdentityResult> userIdentities;
}
