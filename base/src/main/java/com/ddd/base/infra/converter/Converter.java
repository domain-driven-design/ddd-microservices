package com.ddd.base.infra.converter;

import com.ddd.base.domain.aggregate.User;
import com.ddd.base.domain.aggregate.UserIdentity;
import com.ddd.base.domain.aggregate.UserIdentityRole;
import com.ddd.base.infra.po.UserIdentityPO;
import com.ddd.base.infra.po.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", typeConversionPolicy = ReportingPolicy.IGNORE)
public interface Converter {
    Converter INSTANCE = Mappers.getMapper(Converter.class);


    User toEntity(UserPO userPO);

    UserIdentity toUserIdentityEntity(UserIdentityPO userIdentityPO, List<UserIdentityRole> roles);

    UserPO toPO(User user);

    UserIdentityPO toIdentityPO(UserIdentity userIdentity);

    UserIdentityPO toUserIdentityPO(UserIdentity userIdentity);
}
