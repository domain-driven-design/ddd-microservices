package com.ddd.base.application.assembler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddd.base.application.dto.OperationLogResponse;
import com.ddd.base.domain.aggregate.operationlog.OperationLog;
import com.ddd.base.infra.persistence.po.OperationLogPO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import utils.page.PageResponse;

@Mapper(componentModel = "spring", typeConversionPolicy = ReportingPolicy.IGNORE, uses = GeneralAssembler.class)
public interface OperationLogAssembler {

    PageResponse<OperationLogResponse> toPageResponse(Page<OperationLogPO> operationLogPage);

    OperationLogResponse toResponse(OperationLog operationLog);

}
