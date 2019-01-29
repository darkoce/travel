package com.zepp.rpp.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zepp.rpp.domains.security.Role;
import com.zepp.rpp.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{
	
	private final RoleRepository roleRepository;
	
	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public List<Role> setRoles() {
		
		return (List<Role>) roleRepository.findAll();
	}

	@Override
	public void saveRole(Role role) {
		roleRepository.save(role);
	}

	public Role findRole(String name) {
		return roleRepository.findByname(name);
	}
}
