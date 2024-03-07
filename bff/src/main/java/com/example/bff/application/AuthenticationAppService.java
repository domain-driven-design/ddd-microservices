package com.example.bff.application;

import com.example.bff.application.dto.UserLoginCommand;
import com.example.bff.application.dto.UserLoginResponse;
import com.example.bff.utils.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationAppService {

    public UserLoginResponse login(UserLoginCommand userLoginCommand) {
        /**
         * 1. check username
         * 2. generate token
         */
        String token = JwtUtil.generateToken(userLoginCommand.getUserName());
        return new UserLoginResponse(token);
    }
}
