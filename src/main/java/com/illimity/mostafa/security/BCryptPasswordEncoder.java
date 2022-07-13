package com.illimity.mostafa.security;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class BCryptPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(String rawPassword) throws NoSuchAlgorithmException {
        String encodedPassword = null;
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(1, hash);
            StringBuilder hexString = new StringBuilder(number.toString(16));
            while (hexString.length() < 64) {
                hexString.insert(0, '0');
            }
            encodedPassword = hexString.toString();
            log.info("Encoded Password: " + encodedPassword);
        }catch (NoSuchAlgorithmException e){
            throw new NoSuchAlgorithmException(e.getMessage());
        }
    return encodedPassword;
    }
    @Override
    public boolean matches(String rawPassword, String encodedPassword) throws NoSuchAlgorithmException {
        return encode(rawPassword).equals(encodedPassword);
    }
}
