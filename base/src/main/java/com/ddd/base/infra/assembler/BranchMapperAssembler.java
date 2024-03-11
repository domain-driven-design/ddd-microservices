package com.ddd.base.infra.assembler;

import com.ddd.base.domain.aggregate.branch.Branch;
import com.ddd.base.infra.persistence.po.BranchPO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", typeConversionPolicy = ReportingPolicy.IGNORE)
public interface BranchMapperAssembler {

    BranchPO toPO(Branch branch);

    Branch toEntity(BranchPO branchPO);
}
