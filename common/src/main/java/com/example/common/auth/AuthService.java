package com.example.common.auth;

import com.example.common.error.UnauthorizedException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Provide parse User Context Object feature.
 */
@Service
public class AuthService {
    public UserContext currentUser() {
        return Optional.ofNullable(UserContextHolder.getContext()).orElseThrow(() -> {
            throw new UnauthorizedException();
        });
    }

    public String currentUserId() {
        return currentUser().getUserId();
    }
}
