package com.svvg.rajbika.productservice.config;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.svvg.rajbika.productservice.model.Product;
import com.svvg.rajbika.productservice.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ChangeLog
public class DatabaseChangeLog {

    @ChangeSet(order = "001", id = "seedDatabase", author = "Sai")
    public void seedDatabase(ProductRepository productRepository) {
        List<Product> productList = new ArrayList<>();
        productList.add(createNewProduct("Movie Tickets", "Description", "1200"));
        productList.add(createNewProduct("Dinner", "RESTAURANT", "60"));
        productList.add(createNewProduct("Netflix", "ENTERTAINMENT", "10"));

        productRepository.insert(productList);
    }

    private Product createNewProduct(String productName, String productDescription, String productPrice) {
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setProductPrice(productPrice);
        return product;
    }
}