package com.auth.fullstackloginpage.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "com.auth.fullstackloginpage.controller",
        "com.auth.fullstackloginpage.model",
        "com.auth.fullstackloginpage.service"
})
public class WebConfig {
}
