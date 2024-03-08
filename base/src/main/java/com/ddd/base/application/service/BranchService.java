package com.ddd.base.application.service;

import com.ddd.base.domain.repository.UserRepository;
import com.ddd.base.infra.persistence.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BranchService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

}
