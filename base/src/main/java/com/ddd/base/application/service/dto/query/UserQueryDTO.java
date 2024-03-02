package com.ddd.base.application.service.dto.query;

import audit.BasePageDTO;
import com.ddd.base.domain.aggregate.UserIdentityRole;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class UserQueryDTO extends BasePageDTO {
    private String id;
    private String name;
    private String status;
    private String maintainBy;
    private String maintainByName;
    private String permissionBranchId;
    private UserIdentityRole role;

}

