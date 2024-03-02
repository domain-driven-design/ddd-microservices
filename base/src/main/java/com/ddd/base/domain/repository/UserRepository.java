package com.ddd.base.domain.repository;

import com.ddd.base.domain.aggregate.User;
import com.ddd.base.domain.aggregate.UserIdentity;

import java.util.List;

public interface UserRepository {

     List<UserIdentity> findUserIdentitiesByUserId(String userId);

    void create(User user);
}
