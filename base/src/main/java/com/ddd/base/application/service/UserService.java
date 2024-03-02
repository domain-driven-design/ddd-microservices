package com.ddd.base.application.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddd.base.application.service.convert.UserMapperConverter;
import com.ddd.base.application.service.dto.UserCreateDTO;
import com.ddd.base.application.service.dto.UserResponse;
import com.ddd.base.application.service.dto.query.UserQueryDTO;
import com.ddd.base.domain.aggregate.User;
import com.ddd.base.domain.repository.UserRepository;
import com.ddd.base.infra.mapper.UserMapper;
import com.ddd.base.infra.po.UserPO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Page<UserResponse> query(UserQueryDTO userQuery) {

        Page<UserPO> page = new Page<>(userQuery.getCurrentPage(), userQuery.getPageSize());

        Page<UserPO> userPOPage = userMapper.selectPage(page, userQuery);

        return UserMapperConverter.INSTANCE.toPageDTO(userPOPage);
    }

    @Transactional
    public UserResponse register(UserCreateDTO createDTO) {
        User user = createDTO.toEntity();
        userRepository.create(user);
        return UserMapperConverter.INSTANCE.toResponse(user);
    }

    public UserResponse disable(String id) {
        return null;
    }


    public UserResponse enable(String id) {
        return null;
    }

    public UserResponse switchIdentity(String id) {
        return null;
    }

}
