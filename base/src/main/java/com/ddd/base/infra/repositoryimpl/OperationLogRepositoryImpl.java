package com.ddd.base.infra.repositoryimpl;

import com.ddd.base.domain.aggregate.operationlog.OperationLog;
import com.ddd.base.domain.repository.OperationLogRepository;
import com.ddd.base.infra.converter.OperationLogMapperConverter;
import com.ddd.base.infra.persistence.mapper.OperationLogMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class OperationLogRepositoryImpl implements OperationLogRepository {
    private final OperationLogMapper operationLogMapper;
    private final OperationLogMapperConverter operationLogMapperConverter;

    @Override
    public void create(OperationLog operationLog) {
        operationLogMapper.insert(operationLogMapperConverter.toPO(operationLog));
    }

}
