package com.ddd.base.application.assembler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddd.base.application.dto.BranchResponse;
import com.ddd.base.domain.aggregate.branch.Branch;
import com.ddd.base.infra.persistence.po.BranchPO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.example.common.utils.page.PageResponse;

@Mapper(componentModel = "spring", typeConversionPolicy = ReportingPolicy.IGNORE, uses = GeneralAssembler.class)
public interface BranchAssembler {

    PageResponse<BranchResponse> toPageResponse(Page<BranchPO> branchPOPage);

    BranchResponse toResponse(Branch branch);
}
