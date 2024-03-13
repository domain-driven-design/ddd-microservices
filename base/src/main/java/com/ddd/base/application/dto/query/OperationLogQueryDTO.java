package com.ddd.base.application.dto.query;

import com.ddd.base.domain.aggregate.operationlog.OperationResult;
import com.ddd.base.domain.aggregate.operationlog.OperationScene;
import com.ddd.base.domain.aggregate.operationlog.OperationType;
import com.example.common.utils.page.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
public class OperationLogQueryDTO extends PageQuery {

    private List<OperationType> operationTypes;
    private List<OperationScene> operationScenes;
    private OperationResult operationResult;
    @NotBlank
    private String operatorId;

}

