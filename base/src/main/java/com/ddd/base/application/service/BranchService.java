package com.ddd.base.application.service;

import com.ddd.base.application.dto.BranchCreateCommand;
import com.ddd.base.application.dto.BranchResponse;
import com.ddd.base.application.dto.query.BranchQueryDTO;
import com.ddd.base.domain.repository.BranchRepository;
import com.ddd.base.infra.persistence.mapper.BranchMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import utils.page.PageResponse;

@Service
@AllArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;


    public PageResponse<BranchResponse> query(BranchQueryDTO queryDTO) {
        return null;
    }

    public BranchResponse create(BranchCreateCommand createDTO) {
        return null;
    }

    public BranchResponse delete(String id) {
        return null;
    }
}
