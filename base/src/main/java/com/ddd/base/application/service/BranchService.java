package com.ddd.base.application.service;

import com.example.common.auth.AuthService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddd.base.application.assembler.BranchAssembler;
import com.ddd.base.application.dto.BranchCreateCommand;
import com.ddd.base.application.dto.BranchResponse;
import com.ddd.base.application.dto.query.BranchQueryDTO;
import com.ddd.base.domain.aggregate.branch.Branch;
import com.ddd.base.domain.repository.BranchRepository;
import com.ddd.base.infra.persistence.mapper.BranchMapper;
import com.ddd.base.infra.persistence.po.BranchPO;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.example.common.utils.page.PageResponse;

@Service
@AllArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;
    private final BranchAssembler assembler;
    private final AuthService authService;


    public PageResponse<BranchResponse> query(BranchQueryDTO queryDTO) {

        Page<BranchPO> page = new Page<>(queryDTO.getPageNumber(), queryDTO.getPageSize());

        LambdaQueryWrapper<BranchPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(queryDTO.getId()), BranchPO::getId, queryDTO.getId())
                .eq(StringUtils.isNotBlank(queryDTO.getL1BranchId()), BranchPO::getId, queryDTO.getL1BranchId())
                .eq(StringUtils.isNotBlank(queryDTO.getName()), BranchPO::getId, queryDTO.getName())
                .eq(StringUtils.isNotBlank(queryDTO.getLevel()), BranchPO::getId, queryDTO.getLevel());

        Page<BranchPO> branchPOPage = branchMapper.selectPage(page, wrapper);

        return assembler.toPageResponse(branchPOPage);
    }

    public BranchResponse create(BranchCreateCommand createDTO) {
        Branch branch = createDTO.toEntity(authService.currentUser().getUserId());
        branchRepository.create(branch);
        return assembler.toResponse(branch);
    }

    public void delete(String id) {
        Branch branch = branchRepository.find(id)
                .orElseThrow(() -> new RuntimeException("Branch not found: " + id));
        branchRepository.delete(branch);
    }
}
