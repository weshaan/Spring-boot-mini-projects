package com.smartinventory.service.impl;

import com.smartinventory.dto.response.InventoryResponse;
import com.smartinventory.entity.Inventory;
import com.smartinventory.entity.InventoryTransaction;
import com.smartinventory.entity.Product;
import com.smartinventory.exception.InsufficientStockException;
import com.smartinventory.exception.ResourceNotFoundException;
import com.smartinventory.repository.InventoryRepository;
import com.smartinventory.repository.InventoryTransactionRepository;
import com.smartinventory.repository.ProductRepository;
import com.smartinventory.service.InventoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final InventoryTransactionRepository transactionRepository;

    public InventoryServiceImpl(
            ProductRepository productRepository,
            InventoryRepository inventoryRepository,
            InventoryTransactionRepository transactionRepository) {

        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public InventoryResponse getByProductId(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product", "id", productId));

        Inventory inventory = inventoryRepository.findByProduct(product)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Inventory", "productId", productId));

        InventoryResponse response = new InventoryResponse();
        response.setProductId(productId);
        response.setProductName(product.getName());
        response.setQuantity(inventory.getQuantity());
        response.setReorderLevel(inventory.getReorderLevel());
        return response;
    }

    @Override
    public void stockIn(Long productId, int quantity, String reason) {
        Inventory inventory = getInventory(productId);
        inventory.setQuantity(inventory.getQuantity() + quantity);
        inventoryRepository.save(inventory);

        saveTransaction(productId, quantity, InventoryTransaction.TransactionType.STOCK_IN, reason);
    }

    @Override
    public void stockOut(Long productId, int quantity, String reason) {
        Inventory inventory = getInventory(productId);

        if (inventory.getQuantity() < quantity) {
            throw new InsufficientStockException(
                    productId, quantity, inventory.getQuantity());
        }

        inventory.setQuantity(inventory.getQuantity() - quantity);
        inventoryRepository.save(inventory);

        saveTransaction(productId, quantity, InventoryTransaction.TransactionType.STOCK_OUT, reason);
    }

    private Inventory getInventory(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product", "id", productId));

        return inventoryRepository.findByProduct(product)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Inventory", "productId", productId));
    }

    private void saveTransaction(
            Long productId,
            int quantity,
            InventoryTransaction.TransactionType type,
            String reason) {

        Product product = productRepository.findById(productId).get();

        InventoryTransaction tx = new InventoryTransaction();
        tx.setProduct(product);
        tx.setQuantity(quantity);
        tx.setType(type);
        tx.setReason(reason);

        transactionRepository.save(tx);
    }
}
