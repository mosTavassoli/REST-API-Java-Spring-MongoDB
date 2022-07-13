package com.illimity.mostafa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.illimity.mostafa.customers"},mongoTemplateRef = CustomersDBConfig.MONGO_TEMPLATE)
public class CustomersDBConfig {
        protected static final String MONGO_TEMPLATE = "customersMongoTemplate";
}
