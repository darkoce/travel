package com.zepp.rpp.services;

import java.util.Set;

import com.zepp.rpp.domains.User;
import com.zepp.rpp.domains.security.UserRole;


public interface UserService {

    User findByUsername(String username);

    User findByEmail (String email);

    User createUser(User user, Set<UserRole> userRoles) throws Exception;

    User save(User user);

    User findOne(long userId);

}
