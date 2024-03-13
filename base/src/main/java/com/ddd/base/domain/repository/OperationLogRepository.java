package com.ddd.base.domain.repository;

import com.ddd.base.domain.aggregate.operationlog.OperationLog;

import java.util.List;

public interface OperationLogRepository {

    List<OperationLog> find(String userId);

    void create(OperationLog operationLog);

}
