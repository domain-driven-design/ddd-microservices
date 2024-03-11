package com.ddd.base.infra.assembler;

import auth.UserIdentityRole;
import com.ddd.base.domain.aggregate.user.User;
import com.ddd.base.domain.aggregate.user.UserIdentity;
import com.ddd.base.infra.persistence.po.UserIdentityPO;
import com.ddd.base.infra.persistence.po.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", typeConversionPolicy = ReportingPolicy.IGNORE)
public interface UserMapperAssembler {

    User toEntity(UserPO userPO);

    UserIdentity toUserIdentityEntity(UserIdentityPO userIdentityPO, List<UserIdentityRole> roles);

    @Mapping(target = "currentIdentityId", source = "user.currentIdentity.id")
    UserPO toPO(User user);

    UserIdentityPO toIdentityPO(UserIdentity userIdentity);

    UserIdentityPO toUserIdentityPO(UserIdentity userIdentity);
}
