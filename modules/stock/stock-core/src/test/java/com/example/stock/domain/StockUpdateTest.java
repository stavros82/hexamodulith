package com.example.stock.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StockUpdateTest {

    @Test
    void createsStockUpdateWithAllFields() {
        Integer itemId = 1;
        int quantity = 25;
        boolean reorderNeeded = true;

        StockUpdate stockUpdate = new StockUpdate(itemId, quantity, reorderNeeded);

        assertThat(stockUpdate.itemId()).isEqualTo(itemId);
        assertThat(stockUpdate.quantity()).isEqualTo(quantity);
        assertThat(stockUpdate.reorderNeeded()).isEqualTo(reorderNeeded);
    }
}
