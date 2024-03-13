package com.ddd.base.infra.repositoryimpl;

import com.ddd.base.domain.aggregate.branch.Branch;
import com.ddd.base.domain.repository.BranchRepository;
import com.ddd.base.infra.converter.BranchMapperConverter;
import com.ddd.base.infra.persistence.mapper.BranchMapper;
import com.ddd.base.infra.persistence.po.BranchPO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class BranchRepositoryImpl implements BranchRepository {
    private final BranchMapper branchMapper;
    private final BranchMapperConverter assembler;

    @Override
    public void create(Branch branch) {
        branchMapper.insert(assembler.toPO(branch));
    }

    @Override
    public Optional<Branch> find(String id) {
        BranchPO branchPO = branchMapper.selectById(id);
        if (Objects.isNull(branchPO)) {
            return Optional.empty();
        }
        return Optional.of(assembler.toEntity(branchPO));
    }

    @Override
    public void delete(Branch branch) {
        branchMapper.deleteById(branch.getId());

    }
}
