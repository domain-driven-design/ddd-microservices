package utils;

import org.springframework.http.ResponseEntity;

import java.util.Objects;

public class ResponseEntityUtil {
    private ResponseEntityUtil() {
    }

    public static <T> T getBody(ResponseEntity<T> responseEntity) {
        T body = responseEntity.getBody();
        if (Objects.isNull(body)) {
            throw new RuntimeException();
        }
        return body;
    }
}
