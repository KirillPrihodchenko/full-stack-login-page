package com.auth.fullstackloginpage.controller;

import com.auth.fullstackloginpage.auth.util.JwtTokenUtil;
import com.auth.fullstackloginpage.dto.JwtCreatedResponse;
import com.auth.fullstackloginpage.dto.UserLoginRequest;
import com.auth.fullstackloginpage.dto.UserRegistrationRequest;
import com.auth.fullstackloginpage.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("login")
    public ResponseEntity<?> logInSystem(
            @RequestBody UserLoginRequest userLoginRequest
    ) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getEmail(),
                        userLoginRequest.getPassword()
                )
        );
        String generatedJwt = null;

        if (authentication.isAuthenticated()) {

            SecurityContextHolder.getContext().setAuthentication(authentication);
            generatedJwt = new JwtCreatedResponse(jwtTokenUtil.generateJwtToken(authentication)).toString();
        }

        return new ResponseEntity<>(
                generatedJwt,
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<?> registrationPage(
            @RequestBody @Valid UserRegistrationRequest userRegistrationRequest) {

        return new ResponseEntity<>(
                userService.createUser(userRegistrationRequest),

                HttpStatus.CREATED
        );
    }
}
