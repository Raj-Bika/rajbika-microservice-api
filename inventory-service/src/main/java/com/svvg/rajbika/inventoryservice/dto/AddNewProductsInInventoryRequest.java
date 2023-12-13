package com.svvg.rajbika.inventoryservice.dto;

import com.svvg.rajbika.inventoryservice.model.AddInventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddNewProductsInInventoryRequest {
    private List<AddInventory> addInventories;
}
