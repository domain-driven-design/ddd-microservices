package com.ddd.base.infra.assembler;

import com.ddd.base.domain.aggregate.user.User;
import com.ddd.base.domain.aggregate.user.UserIdentity;
import auth.UserIdentityRole;
import com.ddd.base.infra.persistence.po.UserIdentityPO;
import com.ddd.base.infra.persistence.po.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", typeConversionPolicy = ReportingPolicy.IGNORE)
public interface UserAssembler {
    UserAssembler INSTANCE = Mappers.getMapper(UserAssembler.class);


    User toEntity(UserPO userPO);

    UserIdentity toUserIdentityEntity(UserIdentityPO userIdentityPO, List<UserIdentityRole> roles);

    UserPO toPO(User user);

    UserIdentityPO toIdentityPO(UserIdentity userIdentity);

    UserIdentityPO toUserIdentityPO(UserIdentity userIdentity);
}
