package com.ddd.base.application.assembler;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", typeConversionPolicy = ReportingPolicy.IGNORE, uses = GeneralAssembler.class)
public interface BranchAssembler {
    BranchAssembler INSTANCE = Mappers.getMapper(BranchAssembler.class);

}
