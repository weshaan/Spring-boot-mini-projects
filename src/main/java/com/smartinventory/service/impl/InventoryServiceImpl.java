package com.smartinventory.service;

import com.smartinventory.dto.response.InventoryResponse;

public interface InventoryService {

    InventoryResponse getByProductId(Long productId);

    void stockIn(Long productId, int quantity, String reason);

    void stockOut(Long productId, int quantity, String reason);
}
