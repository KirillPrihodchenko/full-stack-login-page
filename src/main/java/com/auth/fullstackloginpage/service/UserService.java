package com.auth.fullstackloginpage.service;

import com.auth.fullstackloginpage.auth.util.UserDetailsImp;
import com.auth.fullstackloginpage.dto.UserRegistrationMapping;
import com.auth.fullstackloginpage.dto.UserRegistrationRequest;
import com.auth.fullstackloginpage.exception.UserNotCreatedException;
import com.auth.fullstackloginpage.model.User;
import com.auth.fullstackloginpage.repository.UserRepository;
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

        checkOnExist(userRegistrationRequest.getEmail());

        User user = userRegistrationMapping.convertToEntity(userRegistrationRequest);
        return userRepository.save(user);
    }

    private void checkOnExist(String email) {

        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new UserNotCreatedException("User with email already exists", new Throwable());
        }
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