package com.svvg.rajbika.userservice.reporsitory;

import com.svvg.rajbika.userservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
