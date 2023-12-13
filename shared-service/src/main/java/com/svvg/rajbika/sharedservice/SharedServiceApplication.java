package com.svvg.rajbika.sharedservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class SharedServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SharedServiceApplication.class, args);
    }
}