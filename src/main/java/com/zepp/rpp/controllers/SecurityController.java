package com.zepp.rpp.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zepp.rpp.domains.User;
import com.zepp.rpp.domains.security.Role;
import com.zepp.rpp.domains.security.UserRole;
import com.zepp.rpp.services.RoleService;
import com.zepp.rpp.services.UserService;
import com.zepp.rpp.utility.SecurityUtility;

@Controller
@RequestMapping("admin")
public class SecurityController {
	
	private final RoleService roleService;
	private final UserService userService;
	
	@Autowired
	public SecurityController(RoleService roleService, UserService userService) {
		this.roleService = roleService;
		this.userService = userService;
	}

	@PreAuthorize("hasRole('MASTERMIND')")
	@GetMapping("/mastermind")
	public String RolesSet() {
		return "secure/mastermind";
	}
	
	@PreAuthorize("hasRole('MASTERMIND')")
	@GetMapping("/mastermind/newrole")
	public String newRole(Model model) {
		model.addAttribute("role", new Role());
		return "secure/addrole";
	}
	
	@PreAuthorize("hasRole('MASTERMIND')")
	@PostMapping("/mastermind/newrole")
	public String newRolePost(@ModelAttribute("role") Role role) throws Exception {
		roleService.saveRole(role);
		return "redirect:/admin/mastermind/rolelist";
	}
	
	@PreAuthorize("hasRole('MASTERMIND')")
	@GetMapping("/mastermind/rolelist")
	public String RolesSet(Model model) {
		model.addAttribute("roles", roleService.setRoles());
		return "secure/rolesset";
	}
	
	@PreAuthorize("hasRole('MASTERMIND')")
	@GetMapping("/mastermind/newuser")
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", roleService.setRoles());
		return "secure/adduser";
	}
	
	@PreAuthorize("hasRole('MASTERMIND')")
	@PostMapping("/mastermind/newuser")
	public String newUserPost(@ModelAttribute("user") User user, @RequestParam("roleName") String roleName) throws Exception {
		user.setPassword(SecurityUtility.passwordEncoder().encode(user.getPassword()));
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, roleService.findRole(roleName)));
		userService.createUser(user, userRoles);
		return "redirect:/admin/mastermind";
	}

}
