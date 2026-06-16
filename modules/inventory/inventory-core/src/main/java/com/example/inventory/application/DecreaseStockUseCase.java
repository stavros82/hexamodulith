package com.example.inventory.application;

import com.example.inventory.domain.event.StockDecreasedEvent;
import com.example.inventory.domain.model.InventoryItem;
import com.example.inventory.port.out.InventoryRepository;


public class DecreaseStockUseCase {

    private final InventoryRepository repo;

    public DecreaseStockUseCase(InventoryRepository repo) {
        this.repo = repo;

    }

    public int handle(Integer itemId, int amount) {
        InventoryItem item = repo.findByItemId(itemId);
        int newQty = item.decrease(amount);
        repo.save(item);


        return newQty;
    }
}
