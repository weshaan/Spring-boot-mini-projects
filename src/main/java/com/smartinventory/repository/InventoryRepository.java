package com.smartinventory.repository;

import com.smartinventory.entity.Inventory;
import com.smartinventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByProduct(Product product);
}
