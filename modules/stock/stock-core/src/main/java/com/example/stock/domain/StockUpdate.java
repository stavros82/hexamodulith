package com.example.stock.domain;

public record StockUpdate(
        Integer itemId,
        int quantity,
        boolean reorderNeeded
) {}
