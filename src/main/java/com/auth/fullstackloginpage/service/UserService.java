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
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserService extends DefaultOAuth2UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRegistrationMapping userRegistrationMapping;

    public UserService(UserRepository userRepository, UserRegistrationMapping userRegistrationMapping) {
        this.userRepository = userRepository;
        this.userRegistrationMapping = userRegistrationMapping;
    }

    public String createUser(UserRegistrationRequest userRegistrationRequest) {

        checkOnExist(userRegistrationRequest.getEmail());

        User user = userRegistrationMapping.convertToEntity(userRegistrationRequest);
        userRepository.save(user);
        return "User registered successfully!";
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

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        return oAuth2User;
    }
}