package com.example.common.utils;

import com.example.common.error.BusinessException;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static com.example.common.error.CommonError.NULL_OBJECT;

public class ResponseEntityUtil {
    private ResponseEntityUtil() {
    }

    public static <T> T getBody(ResponseEntity<T> responseEntity) {
        T body = responseEntity.getBody();
        if (Objects.isNull(body)) {
            throw new BusinessException(NULL_OBJECT);
        }
        return body;
    }
}
