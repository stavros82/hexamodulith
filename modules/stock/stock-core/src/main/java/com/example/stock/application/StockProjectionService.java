package com.example.stock.application;

import com.example.stock.port.out.StockProjectionRepository;

public class StockProjectionService {

    private final StockProjectionRepository repo;

    public StockProjectionService(StockProjectionRepository repo) {
        this.repo = repo;
    }

    public void updateProjection(Integer itemId, int newQty) {
        repo.updateStockLevel(itemId, newQty);
    }

    public void triggerReorder(Integer itemId, int qty) {
        System.out.println("Reorder triggered for itemId: " + itemId + " (qty=" + qty + ")");
        repo.markForReorder(itemId);
    }
}
