package com.svvg.rajbika.productservice;

import com.svvg.rajbika.productservice.dto.ProductRequest;
import com.svvg.rajbika.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductServiceApplicationTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductRepository productRepository;

//    static {
//        mongoDBContainer.start();
//    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry) {
        dymDynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }


    @Test
    public void shouldCreateProduct() throws Exception {
//        ProductRequest productRequest = ProductRequest.builder()
//                .productDescription("Test Description")
//                .productName("Test Product")
//                .productPrice("1200")
//                .skuCode("test_product")
//                .build();
//        String productRequestString = objectMapper.writeValueAsString(productRequest);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(productRequestString)
//        ).andExpect(status().isCreated());
    }



}
