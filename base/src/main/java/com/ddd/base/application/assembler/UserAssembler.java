package com.ddd.base.application.assembler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddd.base.application.dto.UserIdentityResponse;
import com.ddd.base.application.dto.UserResponse;
import com.ddd.base.domain.aggregate.user.User;
import com.ddd.base.domain.aggregate.user.UserIdentity;
import com.ddd.base.infra.persistence.po.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.example.common.utils.page.PageResponse;

import java.util.List;

@Mapper(componentModel = "spring", typeConversionPolicy = ReportingPolicy.IGNORE, uses = GeneralAssembler.class)
public interface UserAssembler {
    UserAssembler INSTANCE = Mappers.getMapper(UserAssembler.class);


    PageResponse<UserResponse> toPageResponse(Page<UserPO> userPO);

    @Mapping(target = "userIdentities", source = "userPO.id")
    @Mapping(target = "currentIdentity", source = "userPO.id")
    UserResponse toResponse(UserPO userPO);

    List<UserIdentityResponse> toIdentityResponses(List<UserIdentity> identities);

    UserResponse toResponse(User user);

    UserIdentityResponse toIdentityResponse(UserIdentity currentIdentity);
}
