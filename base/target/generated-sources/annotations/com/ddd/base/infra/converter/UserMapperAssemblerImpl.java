package com.ddd.base.infra.converter;

import auth.UserIdentityRole;
import com.ddd.base.domain.aggregate.user.User;
import com.ddd.base.domain.aggregate.user.User.UserBuilder;
import com.ddd.base.domain.aggregate.user.UserIdentity;
import com.ddd.base.domain.aggregate.user.UserIdentity.UserIdentityBuilder;
import com.ddd.base.infra.persistence.po.UserIdentityPO;
import com.ddd.base.infra.persistence.po.UserPO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-13T01:04:05+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (Oracle Corporation)"
)
@Component
public class UserMapperAssemblerImpl implements UserMapperConverter {

    @Override
    public User toEntity(UserPO userPO) {
        if ( userPO == null ) {
            return null;
        }

        UserBuilder<?, ?> user = User.builder();

        user.id( userPO.getId() );
        user.createdBy( userPO.getCreatedBy() );
        user.createdTime( userPO.getCreatedTime() );
        user.updatedBy( userPO.getUpdatedBy() );
        user.updatedTime( userPO.getUpdatedTime() );
        user.name( userPO.getName() );
        user.abnormalBatch( userPO.getAbnormalBatch() );
        user.status( userPO.getStatus() );
        user.deleted( userPO.getDeleted() );
        user.maintainBy( userPO.getMaintainBy() );
        user.maintainByName( userPO.getMaintainByName() );
        user.maintainTime( userPO.getMaintainTime() );

        return user.build();
    }

    @Override
    public UserIdentity toUserIdentityEntity(UserIdentityPO userIdentityPO, List<UserIdentityRole> roles) {
        if ( userIdentityPO == null && roles == null ) {
            return null;
        }

        UserIdentityBuilder<?, ?> userIdentity = UserIdentity.builder();

        if ( userIdentityPO != null ) {
            userIdentity.id( userIdentityPO.getId() );
            userIdentity.createdBy( userIdentityPO.getCreatedBy() );
            userIdentity.createdTime( userIdentityPO.getCreatedTime() );
            userIdentity.updatedBy( userIdentityPO.getUpdatedBy() );
            userIdentity.updatedTime( userIdentityPO.getUpdatedTime() );
            userIdentity.permissionBranchId( userIdentityPO.getPermissionBranchId() );
            userIdentity.userId( userIdentityPO.getUserId() );
        }
        if ( roles != null ) {
            List<UserIdentityRole> list = roles;
            if ( list != null ) {
                userIdentity.roles( new ArrayList<UserIdentityRole>( list ) );
            }
        }

        return userIdentity.build();
    }

    @Override
    public UserPO toPO(User user) {
        if ( user == null ) {
            return null;
        }

        UserPO userPO = new UserPO();

        userPO.setCurrentIdentityId( userCurrentIdentityId( user ) );
        userPO.setName( user.getName() );
        userPO.setAbnormalBatch( user.getAbnormalBatch() );
        userPO.setStatus( user.getStatus() );
        userPO.setDeleted( user.getDeleted() );
        userPO.setMaintainBy( user.getMaintainBy() );
        userPO.setMaintainByName( user.getMaintainByName() );
        userPO.setMaintainTime( user.getMaintainTime() );
        userPO.setId( user.getId() );
        userPO.setCreatedBy( user.getCreatedBy() );
        userPO.setCreatedTime( user.getCreatedTime() );
        userPO.setUpdatedBy( user.getUpdatedBy() );
        userPO.setUpdatedTime( user.getUpdatedTime() );

        return userPO;
    }

    @Override
    public UserIdentityPO toIdentityPO(UserIdentity userIdentity) {
        if ( userIdentity == null ) {
            return null;
        }

        UserIdentityPO userIdentityPO = new UserIdentityPO();

        userIdentityPO.setPermissionBranchId( userIdentity.getPermissionBranchId() );
        userIdentityPO.setUserId( userIdentity.getUserId() );
        userIdentityPO.setId( userIdentity.getId() );
        userIdentityPO.setCreatedBy( userIdentity.getCreatedBy() );
        userIdentityPO.setCreatedTime( userIdentity.getCreatedTime() );
        userIdentityPO.setUpdatedBy( userIdentity.getUpdatedBy() );
        userIdentityPO.setUpdatedTime( userIdentity.getUpdatedTime() );

        return userIdentityPO;
    }

    @Override
    public UserIdentityPO toUserIdentityPO(UserIdentity userIdentity) {
        if ( userIdentity == null ) {
            return null;
        }

        UserIdentityPO userIdentityPO = new UserIdentityPO();

        userIdentityPO.setPermissionBranchId( userIdentity.getPermissionBranchId() );
        userIdentityPO.setUserId( userIdentity.getUserId() );
        userIdentityPO.setId( userIdentity.getId() );
        userIdentityPO.setCreatedBy( userIdentity.getCreatedBy() );
        userIdentityPO.setCreatedTime( userIdentity.getCreatedTime() );
        userIdentityPO.setUpdatedBy( userIdentity.getUpdatedBy() );
        userIdentityPO.setUpdatedTime( userIdentity.getUpdatedTime() );

        return userIdentityPO;
    }

    private String userCurrentIdentityId(User user) {
        if ( user == null ) {
            return null;
        }
        UserIdentity currentIdentity = user.getCurrentIdentity();
        if ( currentIdentity == null ) {
            return null;
        }
        String id = currentIdentity.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
