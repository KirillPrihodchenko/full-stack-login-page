package com.auth.fullstackloginpage.service;

import com.auth.fullstackloginpage.dto.UserRegistrationMapping;
import com.auth.fullstackloginpage.dto.UserRegistrationRequest;
import com.auth.fullstackloginpage.exception.UserNotCreatedException;
import com.auth.fullstackloginpage.model.User;
import com.auth.fullstackloginpage.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

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
}