package error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemException extends AbstractException {

    public SystemException() {
        super(CommonErrorCode.COMM500);
    }

    public SystemException(IErrorCode errorCode) {
        super(errorCode);
    }

    public SystemException(IErrorCode errorCode, Object... params) {
        super(errorCode, params);
    }

}
