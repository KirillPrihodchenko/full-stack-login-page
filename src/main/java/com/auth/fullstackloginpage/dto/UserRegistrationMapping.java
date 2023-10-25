package com.auth.fullstackloginpage.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationMapping {

    private final ModelMapper modelMapper;

    public UserRegistrationMapping(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public com.auth.fullstackloginpage.model.User convertToEntity(
            UserRegistrationRequest userRegistrationRequest) {

        return  modelMapper.map(userRegistrationRequest, com.auth.fullstackloginpage.model.User.class);
    }
}