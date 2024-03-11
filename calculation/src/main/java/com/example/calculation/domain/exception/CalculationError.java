package com.example.calculation.domain.exception;

import error.ErrorCodePrefix;
import error.IError;

public enum CalculationError implements IError {

    NO_MATCHED_CALCULATION_SERVICE(1, "fail to find calculation service with mode {0}"),
    WRITE_FLOW_ERROR(2, "fail to write flow into the target file {0}"),
    ;

    private final int errorCodeNumber;
    private final String message;

    CalculationError(int errorCodeNumber, String message) {
        this.errorCodeNumber = errorCodeNumber;
        this.message = message;
    }

    @Override
    public int getErrorCodeNumber() {
        return this.errorCodeNumber;
    }

    @Override
    public ErrorCodePrefix getErrorCodePrefix() {
        return ErrorCodePrefix.CALCULATION;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
