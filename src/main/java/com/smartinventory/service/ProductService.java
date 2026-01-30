package com.smartinventory.service;

import com.smartinventory.dto.request.ProductRequest;
import com.smartinventory.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductResponse create(ProductRequest request);

    ProductResponse getById(Long id);

    Page<ProductResponse> getAll(Pageable pageable);

    ProductResponse update(Long id, ProductRequest request);

    void delete(Long id);
}
