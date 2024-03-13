package com.ddd.base.domain.aggregate.operationlog;

import com.example.common.domain.AbstractEntity;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class OperationLog extends AbstractEntity {

    private OperationType operationType;
    private OperationScene operationScene;
    private OperationResult operationResult;
    private String operatorIp;
    private String operatorId;
    private String operatorName;
    private String additionalInfo;

}
