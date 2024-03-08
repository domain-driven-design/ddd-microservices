package com.ddd.base.application.dto.query;

import utils.page.PageQuery;
import com.ddd.base.domain.aggregate.UserIdentityRole;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class UserQueryDTO extends PageQuery {
    private String id;
    private String name;
    private String status;
    private String maintainBy;
    private String maintainByName;
    private String permissionBranchId;
    private UserIdentityRole role;

}

