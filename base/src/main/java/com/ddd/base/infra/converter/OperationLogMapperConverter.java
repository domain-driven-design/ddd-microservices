package com.ddd.base.infra.converter;

import com.ddd.base.domain.aggregate.operationlog.OperationLog;
import com.ddd.base.infra.persistence.po.OperationLogPO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", typeConversionPolicy = ReportingPolicy.IGNORE)
public interface OperationLogMapperConverter {

    OperationLogPO toPO(OperationLog operationLog);

    OperationLog toEntity(OperationLogPO operationLogPO);
}
