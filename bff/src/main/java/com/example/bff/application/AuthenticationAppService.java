package com.example.bff.application;

import com.example.bff.application.dto.UserLoginCommand;
import com.example.bff.application.dto.UserLoginResponse;
import com.example.bff.domain.UserContext;
import com.example.bff.infrastructure.api.UserClient;
import com.example.bff.infrastructure.api.dto.UserQuery;
import com.example.bff.infrastructure.api.dto.UserResult;
import com.example.bff.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utils.ResponseEntityUtil;
import utils.page.PageResult;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AuthenticationAppService {

    public final UserClient userClient;

    public UserLoginResponse login(UserLoginCommand userLoginCommand) {
        /**
         * 1. Check username and password in base service TODO, Modify this section according to where the platform user password is stored
         * 2. Generate token
         */
        try {
            UserContext userContext = buildUserContext(userLoginCommand.getUserName());
            String token = JwtUtil.generateToken(userContext);
            return new UserLoginResponse(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public UserContext verifyToken(String userToken) {
        return JwtUtil.getUserContextFromToken(userToken);
    }

    private UserContext buildUserContext(String userName) {
        ResponseEntity<PageResult<UserResult>> responseEntity = userClient.query(UserQuery.builder().name(userName).build());
        PageResult<UserResult> pageResult = ResponseEntityUtil.getBody(responseEntity);
        List<UserResult> records = pageResult.getRecords();
        UserResult userResult;

        if (records.size() == 1) {
            userResult = records.get(0);
        } else {
            throw new RuntimeException("user not found");
        }
        return UserContext.builder().userId(userResult.getId()).userName(userResult.getName())
                // TODO  IdentityId confirm with Team
                .build();
    }
}
