package com.illimity.mostafa.customers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.illimity.mostafa.security.PasswordEncoder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customers {
    @Id
    private String customerId;
    @NotNull
    @NotEmpty
    private String fiscalcode;
    private String name;
    private String surname;
    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @NotEmpty
    private String password;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    private Status status;
    @Past
    private LocalDateTime lastLoginDate;
    @NotNull
    @NotEmpty
    private String phoneNumber;
    @NotNull
    @NotEmpty
    private String email;

    public Customers(String fiscalcode,
                     String name,
                     String surname,
                     String username,
                     String password,
                     LocalDateTime createdDate,
                     LocalDateTime lastModifiedDate,
                     Status status,
                     LocalDateTime lastLoginDate,
                     String phoneNumber,
                     String email) {
        this.fiscalcode = fiscalcode;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.status = status;
        this.lastLoginDate = lastLoginDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Customers(String fiscalcode,
                     String name,
                     String surname,
                     String phoneNumber,
                     Status status,
                     String email) {
        this.fiscalcode = fiscalcode;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.email = email;
    }

    public Customers toCustomers(PasswordEncoder passwordEncoder) throws NoSuchAlgorithmException {
        return new Customers(
                fiscalcode, name, surname, username, passwordEncoder.encode(password),
                createdDate, lastModifiedDate, status, lastLoginDate, phoneNumber, email);
    }

}
