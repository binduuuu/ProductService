package com.example.product_service.entity;

import lombok.Data;

@Data
public class ProductInventory {
    private String productInventoryId;
    private int productQuantity;
    private String productId;
    private String merchantId;
    private String productCategoryId;

}
