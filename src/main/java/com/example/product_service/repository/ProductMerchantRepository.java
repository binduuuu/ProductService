package com.example.product_service.repository;

import com.example.product_service.entity.ProductMerchant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductMerchantRepository extends MongoRepository<ProductMerchant, String> {
}
