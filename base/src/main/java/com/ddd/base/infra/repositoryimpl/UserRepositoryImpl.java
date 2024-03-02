package com.ddd.base.infra.repositoryimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ddd.base.domain.aggregate.User;
import com.ddd.base.domain.aggregate.UserIdentity;
import com.ddd.base.domain.aggregate.UserIdentityRole;
import com.ddd.base.domain.repository.UserRepository;
import com.ddd.base.infra.converter.Converter;
import com.ddd.base.infra.mapper.UserIdentityMapper;
import com.ddd.base.infra.mapper.UserIdentityRoleMapper;
import com.ddd.base.infra.mapper.UserMapper;
import com.ddd.base.infra.po.UserIdentityPO;
import com.ddd.base.infra.po.UserIdentityRolePO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserMapper userMapper;
    private final UserIdentityMapper userIdentityMapper;
    private final UserIdentityRoleMapper userIdentityRoleMapper;


    @Override
    public List<UserIdentity> findUserIdentitiesByUserId(@NotBlank String userId) {
        List<UserIdentity> userIdentities = new ArrayList<>();
        LambdaQueryWrapper<UserIdentityPO> identityWrapper = new LambdaQueryWrapper<>();
        identityWrapper.eq(UserIdentityPO::getUserId, userId);
        List<UserIdentityPO> userIdentityPOS = userIdentityMapper.selectList(identityWrapper);

        userIdentityPOS.forEach(
                identityPO -> {
                    List<UserIdentityRolePO> rolePOS = getUserIdentityRoles(identityPO.getId());
                    List<UserIdentityRole> roles =
                            rolePOS.stream().map(UserIdentityRolePO::getRole).distinct().collect(Collectors.toList());
                    UserIdentity userIdentity = Converter.INSTANCE.toUserIdentityEntity(identityPO, roles);

                    userIdentities.add(userIdentity);
                }
        );

        return userIdentities;
    }

    @Override
    public void create(User user) {
        userMapper.insert(Converter.INSTANCE.toPO(user));
        user.getUserIdentity().forEach(
                userIdentity -> {
                    userIdentityMapper.insert(Converter.INSTANCE.toIdentityPO(userIdentity));

                    userIdentity.getRoles().forEach(insertRoles(userIdentity)
                    );

                }
        );

    }

    private Consumer<UserIdentityRole> insertRoles(UserIdentity userIdentity) {
        return userIdentityRole -> {
            UserIdentityRolePO rolePO = buildRolePo(userIdentity, userIdentityRole);
            userIdentityRoleMapper.insert(rolePO);
        };
    }


    private UserIdentityRolePO buildRolePo(UserIdentity userIdentity, UserIdentityRole userIdentityRole) {
        UserIdentityRolePO rolePO = new UserIdentityRolePO();
        rolePO.setRole(userIdentityRole);
        rolePO.setUserIdentityId(userIdentity.getId());
        rolePO.setId(UUID.randomUUID().toString());
        rolePO.setCreatedBy(userIdentity.getCreatedBy());
        rolePO.setCreatedTime(OffsetDateTime.now());
        rolePO.setUpdatedBy(userIdentity.getUpdatedBy());
        rolePO.setUpdatedTime(OffsetDateTime.now());
        return rolePO;
    }

    private List<UserIdentityRolePO> getUserIdentityRoles(@NotBlank String identityId) {
        LambdaQueryWrapper<UserIdentityRolePO> roleWrapper = new LambdaQueryWrapper<>();
        roleWrapper.eq(UserIdentityRolePO::getUserIdentityId, identityId);
        return userIdentityRoleMapper.selectList(roleWrapper);
    }

}
