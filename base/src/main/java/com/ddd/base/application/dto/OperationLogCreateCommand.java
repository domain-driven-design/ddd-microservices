package com.ddd.base.application.dto;

import com.ddd.base.domain.aggregate.operationlog.OperationLog;
import com.ddd.base.domain.aggregate.operationlog.OperationResult;
import com.ddd.base.domain.aggregate.operationlog.OperationScene;
import com.ddd.base.domain.aggregate.operationlog.OperationType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;


@Data
public class OperationLogCreateCommand {

    @NotNull
    private OperationType operationType;
    @NotNull
    private OperationScene operationScene;
    @NotNull
    private OperationResult operationResult;
    @NotBlank
    private String operatorIp;

    private String additionalInfo;

    public OperationLog toEntity(String userId, String userName) {
        return OperationLog.builder()
                .id(UUID.randomUUID().toString())
                .operationType(this.operationType)
                .operationScene(this.operationScene)
                .operationResult(this.operationResult)
                .operatorIp(this.operatorIp)
                .additionalInfo(this.additionalInfo)
                .operatorId(userId)
                .operatorName(userName)
                .updatedBy(userId)
                .updatedTime(OffsetDateTime.now())
                .createdBy(userId)
                .createdTime(OffsetDateTime.now())
                .build();
    }
}

