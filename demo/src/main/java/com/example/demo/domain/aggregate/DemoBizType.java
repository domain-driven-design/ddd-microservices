package com.example.demo.domain.aggregate;

import bizidgenerator.domain.BizType;

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
