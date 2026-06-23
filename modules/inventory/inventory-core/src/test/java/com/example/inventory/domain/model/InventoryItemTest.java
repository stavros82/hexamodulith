package com.example.inventory.domain.model;

import org.junit.jupiter.api.Test;

class InventoryItemTest {

    @Test
    void createWithNameInitializesCorrectly() {
        InventoryItem item = new InventoryItem("Test Item");

        assert item.getItemId() == null;
        assert item.getName().equals("Test Item");
        assert item.getQuantity() == 0;
        assert !item.isReorderNeeded();
    }

    @Test
    void updateStockChangesQuantity() {
        InventoryItem item = new InventoryItem(1, 10, "Item");

        item.updateStock(25);

        assert item.getQuantity() == 25;
    }

    @Test
    void markReorderNeededUpdatesFlag() {
        InventoryItem item = new InventoryItem(1, 10, "Item");

        item.markReorderNeeded(true);

        assert item.isReorderNeeded();
    }

    @Test
    void decreaseReducesQuantity() {
        InventoryItem item = new InventoryItem(1, 10, "Item");

        int result = item.decrease(3);

        assert result == 7;
        assert item.getQuantity() == 7;
    }

    @Test
    void increaseIncreasesQuantity() {
        InventoryItem item = new InventoryItem(1, 10, "Item");

        int result = item.increase(5);

        assert result == 15;
        assert item.getQuantity() == 15;
    }
}
