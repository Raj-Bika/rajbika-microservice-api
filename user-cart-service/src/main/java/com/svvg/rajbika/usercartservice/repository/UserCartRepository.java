package com.svvg.rajbika.usercartservice.repository;

import com.svvg.rajbika.usercartservice.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserCartRepository extends MongoRepository<Cart, String> {

}
