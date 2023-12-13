package com.svvg.rajbika.productservice.service;

import com.svvg.rajbika.inventoryservice.dto.AddNewProductsInInventoryRequest;
import com.svvg.rajbika.inventoryservice.model.AddInventory;
import com.svvg.rajbika.productservice.dto.ProductRequest;
import com.svvg.rajbika.productservice.dto.ProductResponse;
import com.svvg.rajbika.productservice.model.Product;
import com.svvg.rajbika.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;
    private static final String INVENTORY_SERVICE_URL = "http://localhost:8087/api/inventory";

    public void createProduct(ProductRequest productRequest) throws Exception {
        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .productPrice(productRequest.getProductPrice())
                .productDescription(productRequest.getProductDescription())
                .skuCode(productRequest.getSkuCode())
                .build();


        AddInventory addInventory = AddInventory.builder().skuCode(product.getSkuCode()).quantity(0).build();
        List<AddInventory> addInventoryList = new ArrayList<>();
        addInventoryList.add(addInventory);
        AddNewProductsInInventoryRequest addNewProductsInInventoryRequest = AddNewProductsInInventoryRequest.builder().addInventories(addInventoryList).build();
        ResponseEntity<HttpStatus> responseEntity = restTemplate.postForEntity(INVENTORY_SERVICE_URL, addNewProductsInInventoryRequest, HttpStatus.class);
        if(responseEntity.getStatusCode() != HttpStatus.CREATED) {
            throw new Exception("Exception");
        }
        productRepository.save(product);
        log.info("Product {} saved successfully", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .productPrice(product.getProductPrice())
                .build();
    }
}
