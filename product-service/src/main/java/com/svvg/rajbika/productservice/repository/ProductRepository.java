package com.svvg.rajbika.productservice.repository;

import com.svvg.rajbika.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {


}
