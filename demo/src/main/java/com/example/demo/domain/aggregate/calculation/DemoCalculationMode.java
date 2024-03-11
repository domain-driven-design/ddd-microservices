package com.example.demo.domain.aggregate.calculation;

import com.example.calculation.domain.aggregate.CalculationMode;

public enum DemoCalculationMode implements CalculationMode {
    DEPOSIT;

    DemoCalculationMode() {}

    @Override
    public String toString() {
        return name();
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getDescription() {
        return null;
    }
}
