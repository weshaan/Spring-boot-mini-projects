package com.smartinventory.controller;

import com.smartinventory.dto.request.ProductRequest;
import com.smartinventory.dto.response.ApiResponse;
import com.smartinventory.dto.response.ProductResponse;
import com.smartinventory.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(
            @Valid @RequestBody ProductRequest request) {

        ProductResponse response = productService.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(productService.getById(id))
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ProductResponse>>> getAllProducts(
            Pageable pageable) {

        return ResponseEntity.ok(
                ApiResponse.success(productService.getAll(pageable))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(productService.update(id, request))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(
            @PathVariable Long id) {

        productService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
