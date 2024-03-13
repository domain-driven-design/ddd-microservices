package com.example.common.error;

public enum CommonError implements IError {

    BAD_REQUEST("Invalid request parameters"),
    UNAUTHORIZED_ACCESS("Authorization verification failed"),
    FORBIDDEN_ACCESS("Access denied"),
    RESOURCE_NOT_FOUND("Resource not found"),
    SYSTEM_INTERNAL_ERROR("System error"),
    GATEWAY_ERROR("Gateway error"),
    SERVER_NOT_AVAILABLE("Service unavailable"),

    // file related errors
    FILE_NOT_EXIST("Provided file {0} does not exist"),
    READ_FILE_FAILED("Something goes wrong when reading file {0}"),
    WRITE_FILE_FAILED("Something goes wrong when writing file {0}"),
    FILE_READING_ERROR("Reading File {0} with errors"),
    PROCESS_JSON_FAILED("Something goes wrong when processing json with data {0}"),

    // other errors
    NULL_OBJECT("Fail to handle null Object {0}"),
    INVALID_OBJECT_TYPE("Object type {0} does not match"),
    MAP_KEY_NOT_EXIST("Fail to get key {0} from map"),
    NO_MATCHED_BUSINESS_TYPE("Provided business type {0} is not found"),
    ILLEGAL_BUSINESS_RULE("Provided business rule {0} is not valid"),
    ILLEGAL_DATA_DICTIONARY("Provided data dictionary {0} is not valid"),
    NUMBER_ERROR("Error occur when handling number {0}"),
    LOCK_FAILED("The lock is occupied, please submit later!"),
    VALIDATION_FAILED("Fail to validate some data"),
    ;

    private final String message;

    CommonError(String message) {
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
