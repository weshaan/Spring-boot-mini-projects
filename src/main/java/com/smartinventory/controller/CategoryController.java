package com.smartinventory.controller;

import com.smartinventory.dto.request.CategoryRequest;
import com.smartinventory.dto.response.ApiResponse;
import com.smartinventory.dto.response.CategoryResponse;
import com.smartinventory.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(
            @Valid @RequestBody CategoryRequest request) {

        CategoryResponse response = categoryService.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategoryById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(categoryService.getById(id))
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CategoryResponse>>> getAllCategories(
            Pageable pageable) {

        return ResponseEntity.ok(
                ApiResponse.success(categoryService.getAll(pageable))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(categoryService.update(id, request))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(
            @PathVariable Long id) {

        categoryService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
