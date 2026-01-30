package com.smartinventory.repository;

import com.smartinventory.entity.InventoryTransaction;
import com.smartinventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryTransactionRepository
        extends JpaRepository<InventoryTransaction, Long> {

    List<InventoryTransaction> findByProduct(Product product);
}
