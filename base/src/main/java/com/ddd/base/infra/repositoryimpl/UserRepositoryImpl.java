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
import com.ddd.base.infra.po.UserPO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserMapper userMapper;
    private final UserIdentityMapper userIdentityMapper;
    private final UserIdentityRoleMapper userIdentityRoleMapper;


    @Override
    public Optional<User> find(@NotBlank String id) {
        UserPO userPO = userMapper.selectById(id);
        if (Objects.isNull(userPO)) {
            return Optional.empty();
        }
        User user = Converter.INSTANCE.toEntity(userPO);
        List<UserIdentity> identities = findUserIdentitiesByUserId(user.getId());
        user.buildUserIdentity(identities);
        return Optional.of(user);
    }

    @Override
    public List<UserIdentity> findUserIdentitiesByUserId(@NotBlank String userId) {
        List<UserIdentity> userIdentities = new ArrayList<>();
        List<UserIdentityPO> userIdentityPOS = getUserIdentityPOsByUserId(userId);

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

        insertUserIdentity(user);

    }


    @Override
    public void update(User user) {
        if (Objects.isNull(user)) {
            return;
        }
        updateAggregate(user);

        LambdaQueryWrapper<UserIdentityPO> identityWrapper = new LambdaQueryWrapper<>();
        identityWrapper.eq(UserIdentityPO::getUserId, user.getId());
        userIdentityMapper.delete(identityWrapper);

        List<String> userIdentityIds =
                user.getUserIdentity().stream().map(UserIdentity::getId).distinct().collect(Collectors.toList());
        LambdaQueryWrapper<UserIdentityRolePO> roleWrapper = new LambdaQueryWrapper<>();
        roleWrapper.in(UserIdentityRolePO::getUserIdentityId, userIdentityIds);
        userIdentityRoleMapper.delete(roleWrapper);

        insertUserIdentity(user);



    }

    @Override
    public void updateAggregate(User user) {
        UserPO userPO = Converter.INSTANCE.toPO(user);
        userMapper.updateById(userPO);
    }

    private void insertUserIdentity(User user) {
        user.getUserIdentity().forEach(
                userIdentity -> {
                    userIdentityMapper.insert(Converter.INSTANCE.toIdentityPO(userIdentity));
                    userIdentity.getRoles().forEach(insertRoles(userIdentity)
                    );

                }
        );
    }

    private List<UserIdentityPO> getUserIdentityPOsByUserId(String userId) {
        LambdaQueryWrapper<UserIdentityPO> identityWrapper = new LambdaQueryWrapper<>();
        identityWrapper.eq(UserIdentityPO::getUserId, userId);
        return userIdentityMapper.selectList(identityWrapper);
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
