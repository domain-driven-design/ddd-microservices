package com.ddd.base.infra.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import auth.UserIdentityRole;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@TableName("user_identity_role")
public class UserIdentityRolePO {
    private String userIdentityId;
    private UserIdentityRole role;

    private String id;
    private String createdBy;
    private OffsetDateTime createdTime;
    private String updatedBy;
    private OffsetDateTime updatedTime;
}
