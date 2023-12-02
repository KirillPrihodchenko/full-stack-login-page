package com.auth.fullstackloginpage.controller;

import com.auth.fullstackloginpage.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//
//@RestController
//@RequestMapping("/")
//public class RedirectionController {
//
//    private final UserRepository userRepository;
//
//    public RedirectionController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<com.auth.fullstackloginpage.model.User>> getAllUsers() {
//
//        return new ResponseEntity<>(
//                userRepository.findAll(),
//                HttpStatus.OK
//        );
//    }
//}