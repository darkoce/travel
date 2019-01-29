package com.zepp.rpp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.zepp.rpp.domains.User;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);
}
