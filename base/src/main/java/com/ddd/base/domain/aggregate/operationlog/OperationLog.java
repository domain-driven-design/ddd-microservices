package com.ddd.base.domain.aggregate.operationlog;

import audit.AggregateAudit;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class OperationLog extends AggregateAudit {

    private OperationType operationType;
    private OperationScene operationScene;
    private OperationResult operationResult;
    private String operatorIp;
    private String operatorId;
    private String operatorName;
    private String additionalInfo;

}
