package error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<Object> handleSystemException(SystemException exception) {
        log.error("[System Exception] An unexpected system exception has occurred, {}",
                exception.getMessage(), exception);
        return ResponseEntity.fail(exception.getError().getErrorCode(), exception.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException exception) {
        log.warn("[Business Exception] An unexpected business exception has occurred, {}",
                exception.getMessage(), exception);
        return ResponseEntity.warning(exception.getError().getErrorCode(), exception.getMessage());
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<Object> handleClientException(ClientException exception) {
        log.error("[Client Exception] An unexpected client exception has occurred, {}",
                exception.getMessage(), exception);
        return ResponseEntity.fail(exception.getError().getErrorCode(), exception.getMessage());
    }

}
