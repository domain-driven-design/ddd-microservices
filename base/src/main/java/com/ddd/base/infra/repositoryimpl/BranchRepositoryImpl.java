package com.ddd.base.infra.repositoryimpl;

import com.ddd.base.domain.repository.BranchRepository;
import com.ddd.base.infra.persistence.mapper.BranchMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class BranchRepositoryImpl implements BranchRepository {
    private final BranchMapper branchMapper;

}
