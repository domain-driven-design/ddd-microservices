package com.ddd.base.application.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddd.base.application.dto.UserIdentityResponse;
import com.ddd.base.application.dto.UserResponse;
import com.ddd.base.domain.aggregate.User;
import com.ddd.base.domain.aggregate.UserIdentity;
import com.ddd.base.infra.po.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", typeConversionPolicy = ReportingPolicy.IGNORE, uses = GeneralConverter.class)
public interface UserMapperConverter {
    UserMapperConverter INSTANCE = Mappers.getMapper(UserMapperConverter.class);


    Page<UserResponse> toPageDTO(Page<UserPO> userPO);

    @Mapping(target = "userIdentities", source = "userPO.id")
    UserResponse toResponse(UserPO userPO);

    List<UserIdentityResponse> toIdentityResponses(List<UserIdentity> identities);

    UserResponse toResponse(User user);
}
