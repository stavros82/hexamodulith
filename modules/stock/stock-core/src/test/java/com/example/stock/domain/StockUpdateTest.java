package com.example.stock.domain;

import org.junit.jupiter.api.Test;

class StockUpdateTest {

    @Test
    void createsStockUpdateWithAllFields() {
        Integer itemId = 1;
        int quantity = 25;
        boolean reorderNeeded = true;

        StockUpdate stockUpdate = new StockUpdate(itemId, quantity, reorderNeeded);

        assert stockUpdate.itemId().equals(itemId);
        assert stockUpdate.quantity() == quantity;
        assert stockUpdate.reorderNeeded() == reorderNeeded;
    }
}
