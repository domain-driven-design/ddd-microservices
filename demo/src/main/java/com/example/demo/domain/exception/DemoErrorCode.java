package com.example.demo.domain.exception;

import error.ErrorGroup;
import error.IError;

public enum DemoErrorCode implements IError {

    CREATE_TICKET_FAILED("Create ticket failed"),
    TICKET_NOT_FOUND("Ticket not found"),
    UPDATE_TICKET_FAILED("Update ticket failed");

    private final String message;

    DemoErrorCode(String message) {
        this.message = message;
    }

    @Override
    public ErrorGroup getErrorGroup() {
        return ErrorGroup.COMMON;
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
