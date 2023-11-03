package com.auth.fullstackloginpage.controller;

import com.auth.fullstackloginpage.dto.UserRegistrationRequest;
import com.auth.fullstackloginpage.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping
//    public List<User> loginInSystem(
//            @RequestBody @Valid UserLoginRequest userLoginRequest
//    ) {
//
//    }

    @PostMapping("/")
    public ResponseEntity<com.auth.fullstackloginpage.model.User> registrationPage(
            @RequestBody @Valid UserRegistrationRequest userRegistrationRequest) {

        return new ResponseEntity<>(
                userService.createUser(userRegistrationRequest),
                HttpStatus.CREATED
        );
    }
}
