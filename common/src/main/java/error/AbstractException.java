package error;

import lombok.Getter;

import java.text.MessageFormat;

@Getter
public abstract class AbstractException extends RuntimeException {

    private final IErrorCode errorCode;
    private final transient Object[] params;

    protected AbstractException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.params = new Object[0];
    }

    protected AbstractException(IErrorCode errorCode, Object... params) {
        super(MessageFormat.format(errorCode.getMessage(), params));
        this.errorCode = errorCode;
        this.params = params;
    }

}
