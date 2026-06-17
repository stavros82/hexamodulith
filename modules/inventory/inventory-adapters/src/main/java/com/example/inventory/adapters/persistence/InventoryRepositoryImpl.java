package com.example.inventory.adapters.persistence;

import com.example.inventory.domain.model.InventoryItem;
import com.example.inventory.port.out.InventoryRepository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryRepositoryImpl implements InventoryRepository {

    private final InventoryJpaRepository jpa;

    public InventoryRepositoryImpl(@Lazy InventoryJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public InventoryItem findByItemId(Integer itemId) {
        InventoryItemEntity e = jpa.findById(itemId)
                .orElseThrow(() -> new RuntimeException("itemId not found: " + itemId));
        return new InventoryItem(e.getItemId(), e.getQuantity(), e.getName());
    }

    @Override
    public InventoryItem save(InventoryItem item) {
        InventoryItemEntity e = new InventoryItemEntity();

        e.setQuantity(item.getQuantity());
        e.setName(item.getName());

       jpa.save(e);
        return new InventoryItem(e.getItemId(), e.getQuantity(), e.getName());
    }
}
