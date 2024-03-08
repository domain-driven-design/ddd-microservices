package com.example.bff.infrastructure.api;


import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable cause) {
        return id -> {
            log.error("Failed to retrieve data, fallback activated. Error: {}", cause.getMessage());
            return null;
        };
    }
}
