package com.example.product_service.controller;

import com.example.product_service.dto.ProductDto;
import com.example.product_service.entity.Product;
import com.example.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<Boolean> addProduct(@RequestBody ProductDto productDto) {
        Boolean inserted = productService.addProduct(productDto);
        if (inserted) {

            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable("productId") String productId) {
        Product product = productService.getProductById(productId).get();
//                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));;
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> product = productService.getAllProducts();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public Boolean updateProduct(@PathVariable String productId, @RequestBody ProductDto productDto) {
        return productService.updateProductById(productId, productDto);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable String productId) {
        productService.deleteProductById(productId);
        return new ResponseEntity<>("Product with ID " + productId + " deleted successfully", HttpStatus.OK);
    }
}
