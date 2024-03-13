package com.ddd.base.application.dto.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.example.common.utils.page.PageQuery;


@Data
@EqualsAndHashCode(callSuper = true)
public class BranchQueryDTO extends PageQuery {
    private String id;
    private String name;
    private String l1BranchId;
    private String level;


}

