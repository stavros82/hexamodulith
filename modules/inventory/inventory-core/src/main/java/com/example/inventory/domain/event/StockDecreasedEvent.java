package com.example.inventory.domain.event;

import com.example.sharedkernel.events.DomainEvent;

public record StockDecreasedEvent(Integer itemId, int newQuantity) implements DomainEvent {}
