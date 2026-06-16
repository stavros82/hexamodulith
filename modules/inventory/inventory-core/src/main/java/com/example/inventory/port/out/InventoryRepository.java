package com.example.inventory.port.out;

import com.example.inventory.domain.model.InventoryItem;

public interface InventoryRepository {
    InventoryItem findByItemId(Integer itemId);
    InventoryItem save(InventoryItem item);
}
