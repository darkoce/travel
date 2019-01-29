package com.zepp.rpp.services;



import java.util.List;

import com.zepp.rpp.domains.security.Role;

public interface RoleService {
	
	List<Role> setRoles();
	void saveRole(Role role);
	public Role findRole(String name);

}
