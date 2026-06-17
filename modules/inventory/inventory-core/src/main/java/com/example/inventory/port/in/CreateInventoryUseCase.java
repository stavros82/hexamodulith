package com.example.inventory.port.in;

import com.example.inventory.domain.model.InventoryItem;

public interface CreateInventoryUseCase {
    InventoryItem createItem(String name);
}

