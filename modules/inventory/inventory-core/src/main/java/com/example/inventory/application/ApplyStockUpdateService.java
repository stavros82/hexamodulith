package com.example.inventory.application;


import com.example.inventory.domain.model.InventoryItem;
import com.example.inventory.port.in.ApplyStockUpdateUseCase;
import com.example.inventory.port.out.InventoryRepository;

public class ApplyStockUpdateService implements ApplyStockUpdateUseCase {

    private final InventoryRepository repo;

    public ApplyStockUpdateService(InventoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public void apply(Integer itemId, int quantity, boolean reorderNeeded) {
        InventoryItem item = repo.findByItemId(itemId);

        item.updateStock(quantity);
        item.markReorderNeeded(reorderNeeded);

        repo.save(item);
    }
}
