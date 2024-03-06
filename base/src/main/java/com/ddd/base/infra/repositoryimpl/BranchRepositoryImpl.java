package com.ddd.base.infra.repositoryimpl;

import com.ddd.base.domain.repository.BranchRepository;
import com.ddd.base.infra.persistence.mapper.UserIdentityMapper;
import com.ddd.base.infra.persistence.mapper.UserIdentityRoleMapper;
import com.ddd.base.infra.persistence.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class BranchRepositoryImpl implements BranchRepository {
    private final UserMapper userMapper;
    private final UserIdentityMapper userIdentityMapper;
    private final UserIdentityRoleMapper userIdentityRoleMapper;

}
