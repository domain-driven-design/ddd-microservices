package com.ddd.base.infra.repositoryimpl;

import com.ddd.base.domain.repository.UserRepository;
import com.ddd.base.infra.mapper.UserIdentityMapper;
import com.ddd.base.infra.mapper.UserIdentityRoleMapper;
import com.ddd.base.infra.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserMapper userMapper;
    private final UserIdentityMapper userIdentityMapper;
    private final UserIdentityRoleMapper userIdentityRoleMapper;
}
