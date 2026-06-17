package com.example.stock.application;

import com.example.stock.domain.StockUpdate;
import com.example.stock.port.in.UpdateStockUseCase;
import com.example.stock.port.out.StockProjectionRepository;

public class UpdateStockService implements UpdateStockUseCase {

    private final StockProjectionRepository repo;

    public UpdateStockService(StockProjectionRepository repo) {
        this.repo = repo;
    }

    @Override
    public void update(StockUpdate stockUpdate) {
        repo.updateStock(stockUpdate);
    }
}
