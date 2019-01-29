package com.zepp.rpp;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zepp.rpp.domains.User;
import com.zepp.rpp.domains.security.Role;
import com.zepp.rpp.domains.security.UserRole;
import com.zepp.rpp.services.UserService;
import com.zepp.rpp.utility.SecurityUtility;

@SpringBootApplication
public class RppApplication implements CommandLineRunner{

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(RppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Role role2 = new Role();
        role2.setName("s	d");
        
        Role role3 = new Role();
        role3.setName("ROLE_MASTERMIND");

        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Adams");
        user1.setUsername("s");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("d"));
        user1.setEmail("JAdams@gmail.com");
        Set<UserRole> userRoles = new HashSet<>();
        Role role1= new Role();
        //role1.setRoleId(1);
        role1.setName("ROLE_ADMIN");

        User user2 = new User();
        user2.setFirstName("Kiza");
        user2.setLastName("Marković");
        user2.setUsername("kiza");
        user2.setPassword(SecurityUtility.passwordEncoder().encode("kiza"));
        user2.setEmail("kiza@gmail.com");
        Set<UserRole> userRolesKiza = new HashSet<>();
        
        
        User user3 = new User();
        user3.setFirstName("Darko");
        user3.setLastName("Cep");
        user3.setUsername("dare");
        user3.setPassword(SecurityUtility.passwordEncoder().encode("dare"));
        user3.setEmail("darkocep@gmail.com");
        Set<UserRole> userRolesDare = new HashSet<>();

        userRoles.add(new UserRole(user1, role1));

        userRolesKiza.add(new UserRole(user2, role2));
        
        userRolesDare.add(new UserRole(user3, role3));

        userService.createUser(user1, userRoles);

        userService.createUser(user2, userRolesKiza);
        
        userService.createUser(user3, userRolesDare);
        
    }

}