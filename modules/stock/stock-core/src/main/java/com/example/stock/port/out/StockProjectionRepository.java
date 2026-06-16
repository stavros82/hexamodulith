package com.example.stock.port.out;

public interface StockProjectionRepository {
    void updateStockLevel(Integer itemId, int qty);
    void markForReorder(Integer itemId);
}
