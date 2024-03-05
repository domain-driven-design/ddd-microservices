package com.ddd.base.application.convert;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddd.base.application.dto.UserIdentityResponse;
import com.ddd.base.application.dto.UserResponse;
import com.ddd.base.domain.aggregate.User;
import com.ddd.base.domain.aggregate.UserIdentity;
import com.ddd.base.domain.aggregate.UserIdentityRole;
import com.ddd.base.infra.po.UserPO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-02T21:55:16+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (Oracle Corporation)"
)
@Component
public class UserMapperConverterImpl implements UserMapperConverter {

    @Autowired
    private GeneralConverter generalConverter;

    @Override
    public Page<UserResponse> toPageDTO(Page<UserPO> userPO) {
        if ( userPO == null ) {
            return null;
        }

        Page<UserResponse> page = new Page<UserResponse>();

        page.setPages( userPO.getPages() );
        page.setRecords( userPOListToUserResponseList( userPO.getRecords() ) );
        page.setTotal( userPO.getTotal() );
        page.setSize( userPO.getSize() );
        page.setCurrent( userPO.getCurrent() );
        List<OrderItem> list1 = userPO.getOrders();
        if ( list1 != null ) {
            page.setOrders( new ArrayList<OrderItem>( list1 ) );
        }
        page.setSearchCount( userPO.isSearchCount() );

        return page;
    }

    @Override
    public UserResponse toResponse(UserPO userPO) {
        if ( userPO == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setUserIdentities( generalConverter.findUserIdentity( userPO.getId() ) );
        userResponse.setName( userPO.getName() );
        userResponse.setCurrentIdentityId( userPO.getCurrentIdentityId() );
        userResponse.setStatus( userPO.getStatus() );
        userResponse.setMaintainBy( userPO.getMaintainBy() );
        userResponse.setMaintainByName( userPO.getMaintainByName() );
        userResponse.setMaintainTime( userPO.getMaintainTime() );

        return userResponse;
    }

    @Override
    public List<UserIdentityResponse> toIdentityResponses(List<UserIdentity> identities) {
        if ( identities == null ) {
            return null;
        }

        List<UserIdentityResponse> list = new ArrayList<UserIdentityResponse>( identities.size() );
        for ( UserIdentity userIdentity : identities ) {
            list.add( userIdentityToUserIdentityResponse( userIdentity ) );
        }

        return list;
    }

    @Override
    public UserResponse toResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setName( user.getName() );
        userResponse.setCurrentIdentityId( user.getCurrentIdentityId() );
        userResponse.setStatus( user.getStatus() );
        userResponse.setMaintainBy( user.getMaintainBy() );
        userResponse.setMaintainByName( user.getMaintainByName() );
        userResponse.setMaintainTime( user.getMaintainTime() );

        return userResponse;
    }

    protected List<UserResponse> userPOListToUserResponseList(List<UserPO> list) {
        if ( list == null ) {
            return null;
        }

        List<UserResponse> list1 = new ArrayList<UserResponse>( list.size() );
        for ( UserPO userPO : list ) {
            list1.add( toResponse( userPO ) );
        }

        return list1;
    }

    protected UserIdentityResponse userIdentityToUserIdentityResponse(UserIdentity userIdentity) {
        if ( userIdentity == null ) {
            return null;
        }

        UserIdentityResponse userIdentityResponse = new UserIdentityResponse();

        userIdentityResponse.setPermissionBranchId( userIdentity.getPermissionBranchId() );
        userIdentityResponse.setUserId( userIdentity.getUserId() );
        List<UserIdentityRole> list = userIdentity.getRoles();
        if ( list != null ) {
            userIdentityResponse.setRoles( new ArrayList<UserIdentityRole>( list ) );
        }

        return userIdentityResponse;
    }
}
