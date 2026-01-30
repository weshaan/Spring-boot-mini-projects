package com.smartinventory.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory_transactions")
public class InventoryTransaction extends BaseEntity {

    public enum TransactionType {
        STOCK_IN,
        STOCK_OUT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransactionType type;

    @Column(nullable = false)
    private int quantity;

    @Column(length = 255)
    private String reason;

    // getters & setters
    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public TransactionType getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getReason() {
        return reason;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
