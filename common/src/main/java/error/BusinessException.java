package error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends AbstractException {

    public BusinessException(IErrorCode errorCode) {
        super(errorCode);
    }

    public BusinessException(IErrorCode errorCode, Object... params) {
        super(errorCode, params);
    }

}
