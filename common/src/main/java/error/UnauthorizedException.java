package error;

public class UnauthorizedException extends AbstractException {

    public UnauthorizedException() {
        super(CommonError.UNAUTHORIZED_ACCESS);
    }

    public UnauthorizedException(IError errorCode) {
        super(errorCode);
    }

    public UnauthorizedException(IError errorCode, String... params) {
        super(errorCode, params);
    }
}
