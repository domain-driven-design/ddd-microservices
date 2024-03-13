package com.ddd.base.application.dto;

import com.ddd.base.domain.aggregate.operationlog.OperationResult;
import com.ddd.base.domain.aggregate.operationlog.OperationScene;
import com.ddd.base.domain.aggregate.operationlog.OperationType;
import lombok.Data;

@Data
public class OperationLogResponse {

    private String id;
    private OperationType operationType;
    private OperationScene operationScene;
    private OperationResult operationResult;
    private String operatorIp;
    private String operatorId;
    private String operatorName;
    private String additionalInfo;

}
