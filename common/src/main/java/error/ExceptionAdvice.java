package error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ErrorResponse> handleSystemException(SystemException exception) {
        log.error("[System Exception] An unexpected system exception has occurred, {}",
                exception.getMessage(), exception);
        ErrorResponse response = ErrorResponse.create(
                exception.getError().getErrorCode(), exception.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException exception) {
        log.warn("[Business Exception] An unexpected business exception has occurred, {}",
                exception.getMessage(), exception);
        ErrorResponse response = ErrorResponse.create(
                exception.getError().getErrorCode(), exception.getMessage(), exception.getPayload());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); //todo
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ErrorResponse> handleClientException(ClientException exception) {
        log.warn("[Client Exception] An unexpected client exception has occurred, {}",
                exception.getMessage(), exception);
        ErrorResponse response = ErrorResponse.create(
                exception.getError().getErrorCode(), exception.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
