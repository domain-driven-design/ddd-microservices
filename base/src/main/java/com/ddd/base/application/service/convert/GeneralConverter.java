package com.ddd.base.application.service.convert;

import com.ddd.base.application.service.dto.UserIdentityResponse;
import com.ddd.base.domain.aggregate.UserIdentity;
import com.ddd.base.domain.repository.UserRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GeneralConverter {
    private final UserRepository userRepository;

    List<UserIdentityResponse> findUserIdentity(String userId) {
        List<UserIdentity> identities = userRepository.findUserIdentitiesByUserId(userId);
        return UserMapperConverter.INSTANCE.toIdentityResponses(identities);
    }
}
