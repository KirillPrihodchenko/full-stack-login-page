package com.auth.fullstackloginpage.service;

import com.auth.fullstackloginpage.auth.util.UserDetailsImp;
import com.auth.fullstackloginpage.dto.UserRegistrationMapping;
import com.auth.fullstackloginpage.dto.UserRegistrationRequest;
import com.auth.fullstackloginpage.exception.UserNotCreatedException;
import com.auth.fullstackloginpage.model.User;
import com.auth.fullstackloginpage.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRegistrationMapping userRegistrationMapping;

    public UserService(UserRepository userRepository, UserRegistrationMapping userRegistrationMapping) {
        this.userRepository = userRepository;
        this.userRegistrationMapping = userRegistrationMapping;
   }

    public User createUser(UserRegistrationRequest userRegistrationRequest) {

        try {
            checkOnExist(userRegistrationRequest.getEmail());

            User user = userRegistrationMapping.convertToEntity(userRegistrationRequest);
            return userRepository.save(user);
        }
        catch (UserNotCreatedException e) {

            throw new UserNotCreatedException("Failed to create user", e);
        }
    }

    private void checkOnExist(String email) {

        userRepository.findUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("User with email: '%s' not found", email)
                ));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.auth.fullstackloginpage.model.User user = userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with email [%s] not found", username)
                ));

        return UserDetailsImp.build(user);
    }
}