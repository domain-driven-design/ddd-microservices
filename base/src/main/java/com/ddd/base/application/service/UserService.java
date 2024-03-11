package com.ddd.base.application.service;

import auth.AuthService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddd.base.application.assembler.UserAssembler;
import com.ddd.base.application.dto.UserCreateCommand;
import com.ddd.base.application.dto.UserResponse;
import com.ddd.base.application.dto.query.UserQueryDTO;
import com.ddd.base.domain.aggregate.user.User;
import com.ddd.base.domain.repository.UserRepository;
import com.ddd.base.infra.persistence.mapper.UserMapper;
import com.ddd.base.infra.persistence.po.UserPO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.page.PageResponse;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserAssembler assembler;
    private final AuthService authService;

    public PageResponse<UserResponse> query(UserQueryDTO userQuery) {

        Page<UserPO> page = new Page<>(userQuery.getPageNumber(), userQuery.getPageSize());

        Page<UserPO> userPOPage = userMapper.selectPage(page, userQuery);

        return assembler.toPageResponse(userPOPage);
    }

    @Transactional
    public UserResponse register(UserCreateCommand createDTO) {
        User user = createDTO.toEntity(authService.currentUser());
        userRepository.create(user);
        return assembler.toResponse(user);
    }

    @Transactional
    public UserResponse disable(String id) {
        User user = userRepository.find(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        user.disable(authService.currentUser().getUserId());
        userRepository.updateAggregate(user);
        return assembler.toResponse(user);
    }

    @Transactional
    public UserResponse enable(String id) {
        User user = userRepository.find(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        user.enable(authService.currentUser().getUserId());
        userRepository.updateAggregate(user);
        return assembler.toResponse(user);
    }
    @Transactional
    public UserResponse switchIdentity(String id, String identityId) {
        User user = userRepository.find(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        user.switchIdentity(identityId, authService.currentUser().getUserId());
        userRepository.update(user);
        return assembler.toResponse(user);
    }
}
