package error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    private String errorCode;
    private String errorMessage;
    private Object data;

    public static ErrorResponse create(String errorCode, String errorMessage, Object data) {
        return ErrorResponse.builder()
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .data(data)
                .build();
    }

}
