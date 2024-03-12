package error;

public interface IError {

    ErrorGroup getErrorGroup();
    String getSubCode();
    String getMessage();

    default String getErrorCode() {
        return getErrorGroup().toString() + "_" + getSubCode();
    }

}
