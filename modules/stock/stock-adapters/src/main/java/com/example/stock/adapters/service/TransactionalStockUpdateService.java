package com.example.stock.adapters.service;


import com.example.sharedkernel.events.StockUpdatedEvent;

import com.example.stock.adapters.messaging.StockEventPublisher;
import com.example.stock.domain.StockUpdate;
import com.example.stock.port.in.UpdateStockUseCase;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionalStockUpdateService {

    private final UpdateStockUseCase updateStockUseCase;
    private final StockEventPublisher events;

    TransactionalStockUpdateService(UpdateStockUseCase updateStockUseCase,StockEventPublisher events) {
        this.updateStockUseCase = updateStockUseCase;
        this.events = events;
    }

    @Transactional

    public void handle(StockUpdate stockUpdate) {
        updateStockUseCase.update(stockUpdate);
        events.publish(new StockUpdatedEvent(UUID.randomUUID(), stockUpdate.itemId(), stockUpdate.quantity(), stockUpdate.reorderNeeded()));
    }
}
