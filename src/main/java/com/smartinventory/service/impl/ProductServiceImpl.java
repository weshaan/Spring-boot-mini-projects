package com.smartinventory.service.impl;

import com.smartinventory.dto.request.ProductRequest;
import com.smartinventory.dto.response.ProductResponse;
import com.smartinventory.entity.Category;
import com.smartinventory.entity.Inventory;
import com.smartinventory.entity.Product;
import com.smartinventory.exception.BadRequestException;
import com.smartinventory.exception.ResourceNotFoundException;
import com.smartinventory.repository.CategoryRepository;
import com.smartinventory.repository.InventoryRepository;
import com.smartinventory.repository.ProductRepository;
import com.smartinventory.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final InventoryRepository inventoryRepository;

    public ProductServiceImpl(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            InventoryRepository inventoryRepository) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public ProductResponse create(ProductRequest request) {

        if (productRepository.existsBySku(request.getSku())) {
            throw new BadRequestException("SKU already exists");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "id", request.getCategoryId()));

        Product product = new Product();
        product.setName(request.getName());
        product.setSku(request.getSku());
        product.setPrice(request.getPrice());
        product.setCategory(category);

        productRepository.save(product);

        // Initialize inventory
        Inventory inventory = new Inventory();
        inventory.setProduct(product);
        inventory.setQuantity(0);
        inventory.setReorderLevel(10);
        inventoryRepository.save(inventory);

        return mapToResponse(product);
    }

    @Override
    public ProductResponse getById(Long id) {
        return mapToResponse(
                productRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Product", "id", id))
        );
    }

    @Override
    public Page<ProductResponse> getAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product", "id", id));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "id", request.getCategoryId()));

        product.setName(request.getName());
        product.setSku(request.getSku());
        product.setPrice(request.getPrice());
        product.setCategory(category);

        return mapToResponse(productRepository.save(product));
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product", "id", id));

        productRepository.delete(product);
    }

    private ProductResponse mapToResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setSku(product.getSku());
        response.setPrice(product.getPrice());
        response.setCategoryName(product.getCategory().getName());
        return response;
    }
}
