package com.ddd.base.domain.aggregate.operationlog;

import datadictionary.Description;

public enum OperationScene implements Description {

    CALCULATION("测算"),
    ;

    private final String description;

    OperationScene(String description) {
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
