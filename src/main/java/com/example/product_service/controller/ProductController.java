package com.example.product_service.controller;

import com.example.product_service.dto.ProductDto;
import com.example.product_service.entity.Product;
import com.example.product_service.entity.ProductMerchant;
import com.example.product_service.exceptionHandler.ApiResponse;
import com.example.product_service.repository.ProductMerchantRepository;
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

    @Autowired
    private ProductMerchantRepository productMerchantRepository;

    @PostMapping("/addProduct")
    public ResponseEntity<Boolean> addProduct(@RequestBody ProductDto productDto) {
        Boolean inserted = productService.addProduct(productDto);
        if (inserted) {
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{productId}")
    public ApiResponse<Product> getProduct(@PathVariable("productId") String productId) {
        Optional<Product> product = productService.getProductById(productId);
        ApiResponse<Product> apiResponse;
        if(!product.isPresent())
        {
            apiResponse = new ApiResponse<>("404", "Check the product id and try again, product not found");
        }
        else
        {
            apiResponse = new ApiResponse<>(product.toString());
        }
        return apiResponse;
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
    public ApiResponse<Product> deleteProductById(@PathVariable String productId) {
//        productService.deleteProductById(productId);
//        return new ResponseEntity<>("Product with ID " + productId + " deleted successfully", HttpStatus.OK);
        ApiResponse<Product> apiResponse;
//        try {
//            productService.deleteProductById(productId);
//            apiResponse = new ApiResponse<>("Deleted");
//        }
//        catch(Exception e) {
//            apiResponse = new ApiResponse<>("404", "Check the product id and try again, product not found");
//        }

        Optional<Product> product = productService.getProductById(productId);
        if(product.isPresent()) {
            productService.deleteProductById(productId);
            apiResponse = new ApiResponse<>("Deleted");
        }
        else
            apiResponse = new ApiResponse<>("404", "Check the product id and try again, product not found");

        return apiResponse;
    }

    @GetMapping("getAllMerchants/{productId}")
    public ResponseEntity<List<ProductMerchant>> getAllMerchants(@PathVariable("productId") String productId) {
        List<ProductMerchant> productMerchant = productMerchantRepository.findByProductId(productId);

        return new ResponseEntity<>(productMerchant, HttpStatus.OK);
    }
}
