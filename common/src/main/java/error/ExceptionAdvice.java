package error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.net.BindException;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ErrorResponse> handleSystemException(SystemException exception) {
        log.error("[System Exception] An unexpected system exception has occurred, {}", exception.getMessage(), exception);
        ErrorResponse response = ErrorResponse.create(exception.getError().getErrorCode(), exception.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException exception) {
        log.info("[Business Exception] An unexpected business exception has occurred, {}", exception.getMessage(), exception);
        ErrorResponse response = ErrorResponse.create(exception.getError().getErrorCode(), exception.getMessage(), exception.getPayload());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ErrorResponse> handleClientException(ClientException exception) {
        log.error("[Client Exception] An unexpected client exception has occurred, {}", exception.getMessage(), exception);
        ErrorResponse response = ErrorResponse.create(exception.getError().getErrorCode(), exception.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(ClientException exception) {
        log.warn("[Client Exception] An unexpected client exception has occurred, {}", exception.getMessage(), exception);
        ErrorResponse response = ErrorResponse.create(exception.getError().getErrorCode(), exception.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
        ErrorResponse response = ErrorResponse.create(CommonError.BAD_REQUEST.getErrorCode(), exception.getMessage(), exception.getConstraintViolations());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class, ServletRequestBindingException.class, TypeMismatchException.class, HttpMessageNotReadableException.class, MethodArgumentNotValidException.class, MissingServletRequestPartException.class, BindException.class,})
    public ResponseEntity<Object> commonBadRequestException(Exception exception) {
        ErrorResponse response = ErrorResponse.create(CommonError.BAD_REQUEST.getErrorCode(), exception.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<Object> notFoundException(Exception exception) {
        ErrorResponse response = ErrorResponse.create(CommonError.RESOURCE_NOT_FOUND.getErrorCode(), exception.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AsyncRequestTimeoutException.class})
    public ResponseEntity<Object> serviceUnavailableException(Exception exception) {
        ErrorResponse response = ErrorResponse.create(CommonError.SERVER_NOT_AVAILABLE.getErrorCode(), exception.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleGeneralException(Throwable exception) {
        ErrorResponse response = ErrorResponse.create(CommonError.SYSTEM_INTERNAL_ERROR.getErrorCode(), exception.getMessage(), null);
        log.error("[System Exception] An unexpected general exception has occurred, {}", exception.getMessage(), exception);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
