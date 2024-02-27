package com.ddd.base.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ddd.base.application.service.dto.UserDTO;
import com.ddd.base.application.service.dto.query.UserQueryDTO;
import com.ddd.base.domain.repository.UserRepository;
import com.ddd.base.infra.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public ResponseEntity<IPage<UserDTO>> query(UserQueryDTO userQuery) {
        return null;
    }
}
