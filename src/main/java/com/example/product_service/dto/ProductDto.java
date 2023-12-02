package com.example.product_service.dto;

import com.example.product_service.entity.ProductCategory;
import lombok.Data;
import java.util.List;

@Data
public class ProductDto {

    private String productName;
    private String productDescription;
    private String imageUrl;
    private List<ProductCategory> productCategories;
    private int price;
    private int count;
    private String merchantId;
}
