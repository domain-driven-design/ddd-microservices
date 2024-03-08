package com.example.bff.infrastructure.api;

import com.example.bff.infrastructure.api.dto.UserQuery;
import com.example.bff.infrastructure.api.dto.UserResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import utils.page.PageResult;

@FeignClient(name = "user-client", url = "http://localhost:8081", fallbackFactory = UserClientFallbackFactory.class)
public interface UserClient {
    @GetMapping("/users")
    ResponseEntity<PageResult<UserResult>> query(@RequestParam UserQuery userQueryDTO);
}
