package com.ddd.base.application.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddd.base.application.assembler.UserMapperAssembler;
import com.ddd.base.application.dto.UserCreateDTO;
import com.ddd.base.application.dto.UserResponse;
import com.ddd.base.application.dto.query.UserQueryDTO;
import com.ddd.base.domain.aggregate.User;
import com.ddd.base.domain.repository.UserRepository;
import com.ddd.base.infra.persistence.mapper.UserMapper;
import com.ddd.base.infra.persistence.po.UserPO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BranchService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

}
