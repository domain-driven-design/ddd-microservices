package com.ddd.base.application.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddd.base.application.service.dto.UserDTO;
import com.ddd.base.application.service.dto.query.UserQueryDTO;
import com.ddd.base.domain.repository.UserRepository;
import com.ddd.base.infra.mapper.UserMapper;
import com.ddd.base.infra.po.UserPO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public ResponseEntity<Page<UserDTO>> query(UserQueryDTO userQuery) {
        Page<UserPO> page = new Page<>(userQuery.getCurrentPage(), userQuery.getPageSize());

        Page<UserPO> userPOPage = userMapper.selectPage(page, userQuery);


        //convert
        // assembler identity and role
        return null;
    }
}
