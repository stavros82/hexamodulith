package com.example.sharedkernel.events;

import java.util.UUID;

public record StockDecreasedEvent(UUID eventId, Integer itemId, int newQuantity) implements DomainEvent {

}
