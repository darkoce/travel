package com.zepp.rpp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.zepp.rpp.domains.security.Role;


public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByname(String name);
}
