package com.example.bff.adapter;

import com.example.bff.application.AuthenticationAppService;
import com.example.bff.application.dto.UserLoginCommand;
import com.example.bff.application.dto.UserLoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthenticationAppService authenticationAppService;

    @PostMapping("/login")
    // 如果 Base 服务有用户名密码这个接口是否应该收到 Base 服务
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginCommand userLoginCommand) {
        return ResponseEntity.ok(authenticationAppService.login(userLoginCommand));
    }
}
