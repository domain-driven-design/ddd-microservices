package com.ddd.base.application.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddd.base.application.assembler.OperationLogAssembler;
import com.ddd.base.application.dto.OperationLogCreateCommand;
import com.ddd.base.application.dto.OperationLogResponse;
import com.ddd.base.application.dto.query.OperationLogQueryDTO;
import com.ddd.base.domain.aggregate.operationlog.OperationLog;
import com.ddd.base.domain.repository.OperationLogRepository;
import com.ddd.base.infra.persistence.mapper.OperationLogMapper;
import com.ddd.base.infra.persistence.po.OperationLogPO;
import com.example.common.auth.AuthService;
import com.example.common.auth.UserContext;
import com.example.common.utils.page.PageResponse;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class OperationLogService {

    private final AuthService authService;
    private final OperationLogRepository operationLogRepository;
    private final OperationLogMapper operationLogMapper;
    private final OperationLogAssembler operationLogAssembler;

    public OperationLogResponse create(OperationLogCreateCommand command) {
        UserContext userContext = authService.currentUser();
        OperationLog operationLog = command.toEntity(userContext.getUserId(), userContext.getUserName());
        operationLogRepository.create(operationLog);
        return operationLogAssembler.toResponse(operationLog);
    }

    public PageResponse<OperationLogResponse> query(OperationLogQueryDTO queryDTO) {
        Page<OperationLogPO> page = new Page<>(queryDTO.getPageNumber(), queryDTO.getPageSize());

        LambdaQueryWrapper<OperationLogPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OperationLogPO::getOperatorId, queryDTO.getOperatorId())
                .eq(Objects.nonNull(queryDTO.getOperationResult()),
                        OperationLogPO::getOperationResult, queryDTO.getOperationResult())
                .in(CollectionUtils.isNotEmpty(queryDTO.getOperationTypes()),
                        OperationLogPO::getOperationType, queryDTO.getOperationTypes())
                .in(CollectionUtils.isNotEmpty(queryDTO.getOperationScenes()),
                        OperationLogPO::getOperationScene, queryDTO.getOperationScenes());
        Page<OperationLogPO> operationLogPage = operationLogMapper.selectPage(page, wrapper);

        return operationLogAssembler.toPageResponse(operationLogPage);
    }

}
