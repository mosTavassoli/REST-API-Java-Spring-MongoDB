package com.illimity.mostafa.config;


import com.illimity.mostafa.security.BCryptPasswordEncoder;
import com.illimity.mostafa.security.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
