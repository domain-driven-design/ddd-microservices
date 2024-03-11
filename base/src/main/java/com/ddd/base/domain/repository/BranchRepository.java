package com.ddd.base.domain.repository;

import com.ddd.base.domain.aggregate.branch.Branch;

import java.util.Optional;

public interface BranchRepository {


    void create(Branch branch);

    Optional<Branch> find(String id);

    void delete(Branch branch);
}
