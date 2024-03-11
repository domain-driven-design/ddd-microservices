package error;

import lombok.Getter;
import lombok.Setter;


/**
 * BusinessException is used to indicate exceptions that are a result of
 * a violation of business rules or business logic failure. This exception
 * should be thrown when an operation cannot be completed successfully due
 * to reasons inherent in the business logic.
 */
@Getter
@Setter
public class BusinessException extends AbstractException {

    public BusinessException(IError errorCode) {
        super(errorCode);
    }

    public BusinessException(IError errorCode, Object... params) {
        super(errorCode, params);
    }

}
