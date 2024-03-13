package com.ddd.base.domain.repository;

import com.ddd.base.domain.aggregate.operationlog.OperationLog;

public interface OperationLogRepository {

    void create(OperationLog operationLog);

}
