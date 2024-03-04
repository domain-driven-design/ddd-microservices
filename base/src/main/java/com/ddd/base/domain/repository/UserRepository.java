package com.ddd.base.domain.repository;

import com.ddd.base.domain.aggregate.User;
import com.ddd.base.domain.aggregate.UserIdentity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> find(String id);

    List<UserIdentity> findUserIdentitiesByUserId(String userId);

    void create(User user);

    void update(User user);

    void updateAggregate(User user);
}
