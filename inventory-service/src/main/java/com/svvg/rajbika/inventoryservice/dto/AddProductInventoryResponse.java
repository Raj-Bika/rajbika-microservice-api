package com.svvg.rajbika.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddProductInventoryResponse {
    private String skuCode;
    private String quantity;
}
