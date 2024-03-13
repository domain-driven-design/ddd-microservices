package com.ddd.base.infra.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ddd.base.domain.aggregate.operationlog.OperationResult;
import com.ddd.base.domain.aggregate.operationlog.OperationScene;
import com.ddd.base.domain.aggregate.operationlog.OperationType;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@TableName("operation_log")
public class OperationLogPO {

    private String id;
    private OperationType operationType;
    private OperationScene operationScene;
    private OperationResult operationResult;
    private String operatorIp;
    private String operatorId;
    private String operatorName;
    private String additionalInfo;

    private String createdBy;
    private OffsetDateTime createdTime;
    private String updatedBy;
    private OffsetDateTime updatedTime;
}
