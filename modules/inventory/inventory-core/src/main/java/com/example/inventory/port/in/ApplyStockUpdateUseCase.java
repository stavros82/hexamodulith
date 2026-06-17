package com.example.inventory.port.in;

public interface ApplyStockUpdateUseCase {
    void apply(Integer itemId, int quantity, boolean reorderNeeded);
}
