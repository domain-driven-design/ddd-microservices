package com.ddd.base.infra.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@TableName("user_identity")
public class UserIdentityPO {
    private String permissionBranchId;
    private String userId;

    private String id;
    private String createdBy;
    private OffsetDateTime createdTime;
    private String updatedBy;
    private OffsetDateTime updatedTime;
}
