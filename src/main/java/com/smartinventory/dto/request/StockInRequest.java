package com.smartinventory.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class StockInRequest {

    @NotNull(message = "Product ID is required")
    private Long productId;

    @Positive(message = "Quantity must be greater than zero")
    private int quantity;

    @Size(max = 255, message = "Reason must be at most 255 characters")
    private String reason;

    // getters & setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
