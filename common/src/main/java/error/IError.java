package error;

public interface IError {

    ErrorCodePrefix getErrorCodePrefix();
    int getErrorCodeNumber();
    String getMessage();

    default String getErrorCode() {
        return getErrorCodePrefix().toString() + String.format("%03d", getErrorCodeNumber());
    }

}
