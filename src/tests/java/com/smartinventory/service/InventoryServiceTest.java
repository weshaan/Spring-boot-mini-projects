package com.smartinventory.service;

import com.smartinventory.entity.Inventory;
import com.smartinventory.entity.Product;
import com.smartinventory.repository.InventoryRepository;
import com.smartinventory.repository.InventoryTransactionRepository;
import com.smartinventory.repository.ProductRepository;
import com.smartinventory.service.impl.InventoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock private ProductRepository productRepository;
    @Mock private InventoryRepository inventoryRepository;
    @Mock private InventoryTransactionRepository txRepository;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @Test
    void stockIn_success() {
        Product product = new Product();
        product.setId(1L);

        Inventory inventory = new Inventory();
        inventory.setQuantity(5);
        inventory.setProduct(product);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(inventoryRepository.findByProduct(product)).thenReturn(Optional.of(inventory));

        inventoryService.stockIn(1L, 5, "Restock");

        assertEquals(10, inventory.getQuantity());
    }
}
