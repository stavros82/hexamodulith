package com.example.inventory.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InventoryItemTest {

    @Test
    void createWithNameInitializesCorrectly() {
        InventoryItem item = new InventoryItem("Test Item");

        assertThat(item.getItemId()).isNull();
        assertThat(item.getName()).isEqualTo("Test Item");
        assertThat(item.getQuantity()).isEqualTo(0);
        assertThat(item.isReorderNeeded()).isFalse();
    }

    @Test
    void updateStockChangesQuantity() {
        InventoryItem item = new InventoryItem(1, 10, "Item");

        item.updateStock(25);

        assertThat(item.getQuantity()).isEqualTo(25);
    }

    @Test
    void markReorderNeededUpdatesFlag() {
        InventoryItem item = new InventoryItem(1, 10, "Item");

        item.markReorderNeeded(true);

        assertThat(item.isReorderNeeded()).isTrue();
    }

    @Test
    void decreaseReducesQuantity() {
        InventoryItem item = new InventoryItem(1, 10, "Item");

        int result = item.decrease(3);

        assertThat(result).isEqualTo(7);
        assertThat(item.getQuantity()).isEqualTo(7);
    }

    @Test
    void increaseIncreasesQuantity() {
        InventoryItem item = new InventoryItem(1, 10, "Item");

        int result = item.increase(5);

        assertThat(result).isEqualTo(15);
        assertThat(item.getQuantity()).isEqualTo(15);
    }
}
