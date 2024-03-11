package com.example.demo.domain.exception;

import error.IErrorCode;

public enum DemoErrorCode implements IErrorCode {

    DEMO001("DEMO001", "Create ticket failed"),
    DEMO002("DEMO002", "Ticket not found"),
    DEMO003("DEMO003", "Update ticket failed");

    private final String code;
    private final String message;

    DemoErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
