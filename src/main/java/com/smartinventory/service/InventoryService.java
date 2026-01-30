package com.smartinventory.service.impl;

import com.smartinventory.dto.request.CategoryRequest;
import com.smartinventory.dto.response.CategoryResponse;
import com.smartinventory.entity.Category;
import com.smartinventory.exception.BadRequestException;
import com.smartinventory.exception.ResourceNotFoundException;
import com.smartinventory.repository.CategoryRepository;
import com.smartinventory.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse create(CategoryRequest request) {
        if (categoryRepository.existsByNameIgnoreCase(request.getName())) {
            throw new BadRequestException("Category already exists");
        }

        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        categoryRepository.save(category);
        return mapToResponse(category);
    }

    @Override
    public CategoryResponse getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "id", id));

        return mapToResponse(category);
    }

    @Override
    public Page<CategoryResponse> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "id", id));

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        return mapToResponse(categoryRepository.save(category));
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "id", id));

        categoryRepository.delete(category);
    }

    private CategoryResponse mapToResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        return response;
    }
}
