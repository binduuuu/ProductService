package com.example.product_service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "product")
public class Product {

    @Id
    private String productId;

    private String productName;
    private String productDescription;
    private String merchantId;
    private String imageUrl;
    private List<ProductCategory> productCategories;
    private int price;
    private int count;
}



