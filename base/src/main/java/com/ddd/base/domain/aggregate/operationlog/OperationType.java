package com.ddd.base.domain.aggregate.operationlog;

import com.example.common.datadictionary.Description;

public enum OperationType implements Description {

    CREATE("创建"),
    UPDATE("更新"),
    DELETE("删除"),
    READ("查询");

    private final String description;

    OperationType(String description) {
        this.description = description;
    }

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
