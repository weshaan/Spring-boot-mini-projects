package com.smartinventory.controller;

import com.smartinventory.dto.request.StockInRequest;
import com.smartinventory.dto.request.StockOutRequest;
import com.smartinventory.dto.response.ApiResponse;
import com.smartinventory.dto.response.InventoryResponse;
import com.smartinventory.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<InventoryResponse>> getInventoryByProduct(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                ApiResponse.success(inventoryService.getByProductId(productId))
        );
    }

    @PostMapping("/in")
    public ResponseEntity<ApiResponse<Void>> stockIn(
            @Valid @RequestBody StockInRequest request) {

        inventoryService.stockIn(
                request.getProductId(),
                request.getQuantity(),
                request.getReason()
        );

        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PostMapping("/out")
    public ResponseEntity<ApiResponse<Void>> stockOut(
            @Valid @RequestBody StockOutRequest request) {

        inventoryService.stockOut(
                request.getProductId(),
                request.getQuantity(),
                request.getReason()
        );

        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
