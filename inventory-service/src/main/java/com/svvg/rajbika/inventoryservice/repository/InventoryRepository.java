package com.svvg.rajbika.inventoryservice.repository;

import com.svvg.rajbika.inventoryservice.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InventoryRepository extends MongoRepository<Inventory, Integer> {
    Optional<Inventory> findBySkuCode(String skuCode);
}
