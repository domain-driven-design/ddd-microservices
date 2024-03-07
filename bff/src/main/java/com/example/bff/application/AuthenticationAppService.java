package com.example.bff.application;

import com.example.bff.application.dto.UserLoginCommand;
import com.example.bff.application.dto.UserLoginResponse;
import com.example.bff.domain.UserContext;
import com.example.bff.infrastructure.repository.UserRepository;
import com.example.bff.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class AuthenticationAppService {

    public final UserRepository userRepository;

    public UserLoginResponse login(UserLoginCommand userLoginCommand) {
        /**
         * 1. check username and password in base service TODO
         * 2. generate token
         */
        try {
            UserContext userContext = buildUserContext(userLoginCommand.getUserName());
            String token = JwtUtil.generateToken(userContext);
            return new UserLoginResponse(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private UserContext buildUserContext(String userName) {
        // TODO fetch User Object from domain，just mock it
        return UserContext.builder().userId("id").userName(userName).currentIdentity(UserContext.ContextUserIdentity.builder().permissionBranchId("001").roles(Arrays.asList(UserContext.UserIdentityRole.USER)).build()).build();
    }
}
