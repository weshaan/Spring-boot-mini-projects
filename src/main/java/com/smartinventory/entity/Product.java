package com.smartinventory.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "products",
        uniqueConstraints = @UniqueConstraint(columnNames = "sku")
)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 100)
    private String sku;

    @Column(nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Inventory inventory;

    // getters & setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }

    public Double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setName(String name) {
        this.name = name;
    }
}
