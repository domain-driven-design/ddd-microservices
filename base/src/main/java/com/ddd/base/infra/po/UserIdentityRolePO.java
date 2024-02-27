package com.ddd.base.infra.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@TableName("user_identity_role")
public class UserIdentityRolePO {
    private String userIdentityId;
    private String role;

    private String id;
    private String createdBy;
    private OffsetDateTime createdTime;
    private String updatedBy;
    private OffsetDateTime updatedTime;
}
