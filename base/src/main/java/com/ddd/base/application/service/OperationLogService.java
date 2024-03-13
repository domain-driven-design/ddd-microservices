package com.ddd.base.application.service;

import auth.AuthService;
import auth.UserContext;
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
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import utils.page.PageResponse;

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
        wrapper.eq(StringUtils.isNotBlank(queryDTO.getOperatorId()),
                        OperationLogPO::getOperatorId, queryDTO.getOperatorId())
                .in(CollectionUtils.isNotEmpty(queryDTO.getOperationTypes()),
                        OperationLogPO::getOperationType, queryDTO.getOperationTypes())
                .in(CollectionUtils.isNotEmpty(queryDTO.getOperationScenes()),
                        OperationLogPO::getOperationScene, queryDTO.getOperationScenes());
        Page<OperationLogPO> operationLogPage = operationLogMapper.selectPage(page, wrapper);

        return operationLogAssembler.toPageResponse(operationLogPage);
    }

}
