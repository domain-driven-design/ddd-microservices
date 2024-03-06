package com.ddd.base.infra.assembler;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", typeConversionPolicy = ReportingPolicy.IGNORE)
public interface BranchAssembler {
    BranchAssembler INSTANCE = Mappers.getMapper(BranchAssembler.class);

}
