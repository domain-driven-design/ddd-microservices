package com.ddd.base.infra.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
public class UserPO {
    private String name;
    private String currentIdentityId;
    private String abnormalBatch;
    private String status;
    private Boolean deleted;
    private String maintainBy;
    private String maintainByName;
    private String maintainTime;
    private Integer version;

    private String id;
    private String createdBy;
    private LocalDateTime createdTime;
    private String updatedBy;
    private LocalDateTime updatedTime;
}
