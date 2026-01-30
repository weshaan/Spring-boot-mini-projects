package com.smartinventory.util;

import com.smartinventory.dto.response.CategoryResponse;
import com.smartinventory.dto.response.ProductResponse;
import com.smartinventory.entity.Category;
import com.smartinventory.entity.Product;

public final class MapperUtil {

    private MapperUtil() {
    }

    /* ================= CATEGORY ================= */

    public static CategoryResponse toCategoryResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        return response;
    }

    /* ================= PRODUCT ================= */

    public static ProductResponse toProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setSku(product.getSku());
        response.setPrice(product.getPrice());
        response.setCategoryName(product.getCategory().getName());
        return response;
    }
}
