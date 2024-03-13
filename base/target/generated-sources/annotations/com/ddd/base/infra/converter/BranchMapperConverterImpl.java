package com.ddd.base.infra.converter;

import com.ddd.base.domain.aggregate.branch.Branch;
import com.ddd.base.domain.aggregate.branch.Branch.BranchBuilder;
import com.ddd.base.infra.persistence.po.BranchPO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-13T16:00:44+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@Component
public class BranchMapperConverterImpl implements BranchMapperConverter {

    @Override
    public BranchPO toPO(Branch branch) {
        if ( branch == null ) {
            return null;
        }

        BranchPO branchPO = new BranchPO();

        branchPO.setParentId( branch.getParentId() );
        branchPO.setName( branch.getName() );
        branchPO.setL1BranchId( branch.getL1BranchId() );
        branchPO.setParentName( branch.getParentName() );
        branchPO.setPathName( branch.getPathName() );
        branchPO.setLevel( branch.getLevel() );
        branchPO.setId( branch.getId() );
        branchPO.setCreatedBy( branch.getCreatedBy() );
        branchPO.setCreatedTime( branch.getCreatedTime() );
        branchPO.setUpdatedBy( branch.getUpdatedBy() );
        branchPO.setUpdatedTime( branch.getUpdatedTime() );

        return branchPO;
    }

    @Override
    public Branch toEntity(BranchPO branchPO) {
        if ( branchPO == null ) {
            return null;
        }

        BranchBuilder<?, ?> branch = Branch.builder();

        branch.id( branchPO.getId() );
        branch.createdTime( branchPO.getCreatedTime() );
        branch.createdBy( branchPO.getCreatedBy() );
        branch.updatedBy( branchPO.getUpdatedBy() );
        branch.updatedTime( branchPO.getUpdatedTime() );
        branch.parentId( branchPO.getParentId() );
        branch.name( branchPO.getName() );
        branch.l1BranchId( branchPO.getL1BranchId() );
        branch.parentName( branchPO.getParentName() );
        branch.pathName( branchPO.getPathName() );
        branch.level( branchPO.getLevel() );

        return branch.build();
    }
}
