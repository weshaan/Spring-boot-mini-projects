package com.smartinventory.service;

import com.smartinventory.dto.request.CategoryRequest;
import com.smartinventory.entity.Category;
import com.smartinventory.repository.CategoryRepository;
import com.smartinventory.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    void createCategory_success() {
        CategoryRequest request = new CategoryRequest();
        request.setName("Books");

        when(categoryRepository.existsByNameIgnoreCase("Books")).thenReturn(false);
        when(categoryRepository.save(any(Category.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        assertNotNull(categoryService.create(request));
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    void getCategoryById_notFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,
                () -> categoryService.getById(1L));
    }
}
