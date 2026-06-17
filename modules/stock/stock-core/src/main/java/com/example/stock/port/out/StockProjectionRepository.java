package com.example.stock.port.out;

import com.example.stock.domain.StockUpdate;

public interface StockProjectionRepository {
    void updateStock(StockUpdate stockUpdate);
}
