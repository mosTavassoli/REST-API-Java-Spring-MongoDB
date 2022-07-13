package com.illimity.mostafa.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MultipleMongoConfig {
    @Primary
    @Bean(name = "customersProperties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.customers")
    public MongoProperties getCustomersProps() throws Exception {
        try {
            return new MongoProperties();

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    @Bean(name = "movementsProperties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.movements")
    public MongoProperties getMovementsProps() throws Exception {

        try {
            return new MongoProperties();

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }

    }
    @Primary
    @Bean(name = "customersMongoTemplate")
    public MongoTemplate customersMongoTemplate() throws Exception {
        return new MongoTemplate(customersMongoDatabaseFactory(getCustomersProps()));
    }

    @Bean(name = "movementsMongoTemplate")
    public MongoTemplate movementsMongoTemplate() throws Exception {
        return new MongoTemplate(movementsMongoDatabaseFactory(getMovementsProps()));
    }
    @Primary
    @Bean
    public MongoDatabaseFactory customersMongoDatabaseFactory(MongoProperties mongo) throws Exception {
        try {
            return new SimpleMongoClientDatabaseFactory(
                    mongo.getUri()
            );
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Bean
    public MongoDatabaseFactory movementsMongoDatabaseFactory(MongoProperties mongo) throws Exception {

        try {
            return new SimpleMongoClientDatabaseFactory(mongo.getUri());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}