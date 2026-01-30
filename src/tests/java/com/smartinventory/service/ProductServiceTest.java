package com.smartinventory.service;

import com.smartinventory.dto.request.ProductRequest;
import com.smartinventory.entity.Category;
import com.smartinventory.repository.CategoryRepository;
import com.smartinventory.repository.InventoryRepository;
import com.smartinventory.repository.ProductRepository;
import com.smartinventory.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock private ProductRepository productRepository;
    @Mock private CategoryRepository categoryRepository;
    @Mock private InventoryRepository inventoryRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void createProduct_success() {
        ProductRequest request = new ProductRequest();
        request.setName("Phone");
        request.setSku("SKU123");
        request.setPrice(50000.0);
        request.setCategoryId(1L);

        Category category = new Category();
        category.setId(1L);

        when(productRepository.existsBySku("SKU123")).thenReturn(false);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        assertNotNull(productService.create(request));
    }
}
