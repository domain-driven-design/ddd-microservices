package com.ddd.base.application.assembler;

import com.ddd.base.application.dto.UserIdentityResponse;
import com.ddd.base.domain.aggregate.User;
import com.ddd.base.domain.aggregate.UserIdentity;
import com.ddd.base.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class GeneralAssembler {
    private final UserRepository userRepository;

    List<UserIdentityResponse> findUserIdentity(String userId) {
        List<UserIdentity> identities = userRepository.findUserIdentitiesByUserId(userId);
        return UserMapperAssembler.INSTANCE.toIdentityResponses(identities);
    }

    UserIdentityResponse findCurrentUserIdentity(String userId) {
        User user = userRepository.find(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        return UserMapperAssembler.INSTANCE.toIdentityResponse(user.getCurrentIdentity());
    }
}
