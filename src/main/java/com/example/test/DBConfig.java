package com.example.test;

import com.mongodb.Mongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * User: mpapini
 * Date: 13/09/16
 * Time: 16:55
 */
public class DBConfig extends AbstractMongoConfiguration {

    private final Logger log = LoggerFactory.getLogger(DBConfig.class);

    @Autowired
    private Mongo mongo;

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Override
    protected String getDatabaseName() {
        return "test";
    }

    @Override
    public Mongo mongo() throws Exception {
        return mongo;
    }

}
