package com.zepp.rpp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.zepp.rpp.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zepp.rpp.domains.User;
import com.zepp.rpp.domains.security.UserRole;
import com.zepp.rpp.repositories.RoleRepository;
import com.zepp.rpp.repositories.UserRepository;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail (String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public User createUser(User user, Set<UserRole> userRoles){
        User localUser = userRepository.findByUsername(user.getUsername());

        if(localUser != null) {
            LOG.info("user {} already exists. Nothing will be done.", user.getUsername());
        } else {
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);

            localUser = userRepository.save(user);
        }

        return localUser;
    }

    @Override
    public User save(User user) {
        log.debug("dodat je novi user " + user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public User findOne(long userId) {
        Optional<User> users = userRepository.findById(userId);
        if(!users.isPresent()){
            throw new NotFoundException("Nije pronađeno putovanje sa Id-ijem: " + userId);
        }
        return users.get();
    }


}
