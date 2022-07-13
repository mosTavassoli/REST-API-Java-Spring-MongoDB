package com.illimity.mostafa.services;


import com.illimity.mostafa.customers.Customers;

import java.net.URI;
import java.security.NoSuchAlgorithmException;


public interface CustomersService {
     Customers processLoginCustomerService(Customers customerInput) throws NoSuchAlgorithmException;
     URI processRegistration(Customers customer) throws NoSuchAlgorithmException;
}
