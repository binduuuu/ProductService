package com.example.product_service.service.impl;

import com.example.product_service.dto.ProductDto;
import com.example.product_service.entity.Product;
import com.example.product_service.entity.ProductMerchant;
import com.example.product_service.repository.ProductMerchantRepository;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMerchantRepository productMerchantRepository;

    @Override
    public Boolean addProduct(ProductDto productDto ) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);
        product.setProductCategoryId(productDto.getProductCategoryId());
        Product product1 = productRepository.save(product);

        ProductMerchant productMerchant = new ProductMerchant();
        productMerchant.setMerchantId(productDto.getMerchantId());
        productMerchant.setProductId(productId);
        productMerchant.setRating(0);
        productMerchant.setStocks(productDto.getCount());
        productMerchantRepository.save(productMerchant);
        return Objects.nonNull(product1);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String productId){
        Optional<Product> product = productRepository.findById(productId);
        return product;
    }

    public Boolean updateProductById(String productId, ProductDto productDto) {
        Product updatedProduct = new Product();
        BeanUtils.copyProperties(productDto, updatedProduct);
        updatedProduct = productRepository.save(updatedProduct);
        return updatedProduct!=null;
    }

    public void deleteProductById(String productId) {
        productRepository.deleteById(productId);
    }
}

