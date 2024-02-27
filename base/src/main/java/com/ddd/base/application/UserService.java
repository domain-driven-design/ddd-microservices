package com.ddd.base.application;

import com.ddd.base.domain.repository.UserRepository;
import com.ddd.base.infra.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
}
