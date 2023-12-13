package com.svvg.rajbika.userservice.config;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.svvg.rajbika.userservice.model.User;
import com.svvg.rajbika.userservice.reporsitory.UserRepository;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.schema.JsonSchemaProperty;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;
import org.springframework.data.mongodb.core.validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ChangeLog
public class DatabaseChangeLog {

    @ChangeSet(order = "001", id = "seedDatabase", author = "Sai")
    public void seedDatabase(UserRepository userRepository) {
        List<User> productList = new ArrayList<>();
        productList.add(createNewUser("Ankit", "Kumar", "ankitkuma@gn.com"));
        productList.add(createNewUser("Nishant", "Sharma", "nish@g.com"));
        productList.add(createNewUser("Puja", "Gour", "pg@admin.com"));

        userRepository.insert(productList);
    }

    private User createNewUser(String firstName, String lastName, String email) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        return user;
    }
}
