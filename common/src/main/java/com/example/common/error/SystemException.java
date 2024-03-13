package com.example.common.error;

import lombok.Getter;
import lombok.Setter;

/**
 * SystemException is used to indicate a variety of system-level error conditions.
 * It is typically thrown when an underlying system issue prevents the normal
 * operation of the application.
 */
@Getter
@Setter
public class SystemException extends AbstractException {

    public SystemException() {
        super(CommonError.SYSTEM_INTERNAL_ERROR);
    }

    public SystemException(IError errorCode) {
        super(errorCode);
    }

    public SystemException(IError errorCode, String... params) {
        super(errorCode, params);
    }

}
