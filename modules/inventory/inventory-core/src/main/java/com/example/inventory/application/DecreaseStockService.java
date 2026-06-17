package com.example.inventory.application;

import com.example.inventory.domain.model.InventoryItem;
import com.example.inventory.port.in.DecreaseStockUseCase;
import com.example.inventory.port.out.InventoryRepository;


public class DecreaseStockService implements DecreaseStockUseCase {

    private final InventoryRepository repo;

    public DecreaseStockService(InventoryRepository repo) {
        this.repo = repo;

    }

    public int handle(Integer itemId, int amount) {
        InventoryItem item = repo.findByItemId(itemId);
        int newQty = item.decrease(amount);
        repo.save(item);


        return newQty;
    }
}
