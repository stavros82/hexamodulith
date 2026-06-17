package com.example.stock.port.in;

import com.example.stock.domain.StockUpdate;

public interface UpdateStockUseCase {
    void update(StockUpdate stockUpdate);
}
