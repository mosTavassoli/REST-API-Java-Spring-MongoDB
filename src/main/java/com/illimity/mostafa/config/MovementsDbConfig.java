
package com.illimity.mostafa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.illimity.mostafa.movements"},mongoTemplateRef = MovementsDbConfig.MONGO_TEMPLATE)
public class MovementsDbConfig {
    protected static final String MONGO_TEMPLATE = "movementsMongoTemplate";
}







