package com.example.sharedkernel.events;

public record StockDecreasedEvent(Integer itemId, int newQuantity) implements DomainEvent {}
