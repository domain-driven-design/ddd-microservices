package com.ddd.base.application.dto.query;

import com.ddd.base.domain.aggregate.operationlog.OperationResult;
import com.ddd.base.domain.aggregate.operationlog.OperationScene;
import com.ddd.base.domain.aggregate.operationlog.OperationType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import utils.page.PageQuery;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
public class OperationLogQueryDTO extends PageQuery {

    private List<OperationType> operationTypes;
    private List<OperationScene> operationScenes;
    private OperationResult operationResult;
    private String operatorId;

}

