package error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseEntity<T> {

    private Status status;
    private String errorCode;
    private T data;

    public ResponseEntity(Status status, String errorCode, T data) {
        this.status = status;
        this.errorCode = errorCode;
        this.data = data;
    }

    public boolean isSuccess() {
        return Status.SUCCESS == status;
    }

    public static ResponseEntity<Object> success() {
        return new ResponseEntity<>(Status.SUCCESS, null, null);
    }
    public static <T> ResponseEntity<T> success(T data) {
        return new ResponseEntity<>(Status.SUCCESS, null, data);
    }

    public static ResponseEntity<Object> warning() {
        return new ResponseEntity<>(Status.WARNING, null, null);
    }
    public static <T> ResponseEntity<T> warning(String errorCode) {
        return new ResponseEntity<>(Status.WARNING, errorCode, null);
    }
    public static <T> ResponseEntity<T> warning(String errorCode, T data) {
        return new ResponseEntity<>(Status.WARNING, errorCode, data);
    }

    public static ResponseEntity<Object> fail(String errorCode) {
        return new ResponseEntity<>(Status.ERROR, errorCode, null);
    }
    public static <T> ResponseEntity<T> fail(String errorCode, T data) {
        return new ResponseEntity<>(Status.ERROR, errorCode, data);
    }

    public enum Status {
        SUCCESS,
        WARNING,
        ERROR
    }

}
