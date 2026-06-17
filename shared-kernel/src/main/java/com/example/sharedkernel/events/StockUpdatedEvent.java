package com.example.sharedkernel.events;

public record StockUpdatedEvent(
        Integer itemId,
        int quantity,
        boolean reorderNeeded
) {}
