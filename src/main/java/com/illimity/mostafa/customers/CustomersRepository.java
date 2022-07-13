package com.illimity.mostafa.customers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomersRepository extends MongoRepository<Customers, String> {
    Customers findCustomersByUsername(String username);

}
