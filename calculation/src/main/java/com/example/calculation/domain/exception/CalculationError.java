package com.example.calculation.domain.exception;

import error.ErrorGroup;
import error.IError;

public enum CalculationError implements IError {

    NO_MATCHED_CALCULATION_SERVICE("fail to find calculation service with mode {0}"),
    WRITE_FLOW_ERROR("fail to write flow into the target file {0}"),
    ;

    private final String message;

    CalculationError(String message) {
        this.message = message;
    }

    @Override
    public ErrorGroup getErrorGroup() {
        return ErrorGroup.CALCULATION;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getSubCode() {
        return this.name();
    }

}
