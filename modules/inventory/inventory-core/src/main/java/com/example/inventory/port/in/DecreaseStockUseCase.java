package com.example.inventory.port.in;

public interface DecreaseStockUseCase {
    int handle(Integer itemId, int amount);
}
