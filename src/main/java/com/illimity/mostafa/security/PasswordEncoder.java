package com.illimity.mostafa.security;


import java.security.NoSuchAlgorithmException;


public interface PasswordEncoder {
    String encode(String rawPassword) throws NoSuchAlgorithmException;
    boolean matches (String rawPassword, String encodedPassword) throws NoSuchAlgorithmException;
}
