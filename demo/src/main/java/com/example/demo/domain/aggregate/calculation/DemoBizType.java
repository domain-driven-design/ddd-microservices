package com.example.demo.domain.aggregate.calculation;

import com.example.common.bizidgenerator.domain.BizType;

public enum DemoBizType implements BizType {
    DEPOSIT;

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getDescription() {
        return null;
    }
}
