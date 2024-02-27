package com.ddd.base.application.service.dto;

import com.ddd.base.domain.aggregate.UserIdentity;
import lombok.Data;

@Data
public class UserDTO {
    private String name;
    private String currentIdentityId;
    private String abnormalBatch;
    private String status;
    private Boolean deleted;
    private String maintainBy;
    private String maintainByName;
    private String maintainTime;
    private String version;
    private UserIdentity userIdentity;
}
