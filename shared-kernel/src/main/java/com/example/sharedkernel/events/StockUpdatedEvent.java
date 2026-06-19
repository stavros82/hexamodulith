package com.example.sharedkernel.events;

import java.util.UUID;

public record StockUpdatedEvent(
        UUID eventId,
        Integer itemId,
        int quantity,
        boolean reorderNeeded
) implements DomainEvent {

}
