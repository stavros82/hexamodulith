package com.example.stock.adapters.service;

import com.example.sharedkernel.events.StockUpdatedEvent;
import com.example.stock.adapters.messaging.StockEventPublisher;
import com.example.stock.domain.StockUpdate;
import com.example.stock.port.in.UpdateStockUseCase;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionalStockUpdateService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionalStockUpdateService.class);
    private final UpdateStockUseCase updateStockUseCase;
    private final StockEventPublisher events;

    TransactionalStockUpdateService(UpdateStockUseCase updateStockUseCase, StockEventPublisher events) {
        this.updateStockUseCase = updateStockUseCase;
        this.events = events;
    }

    @Transactional
    public void handle(StockUpdate stockUpdate) {
        logger.info("Handling stock update: itemId={}, quantity={}, reorderNeeded={}",
                stockUpdate.itemId(), stockUpdate.quantity(), stockUpdate.reorderNeeded());
        updateStockUseCase.update(stockUpdate);
        StockUpdatedEvent event = new StockUpdatedEvent(UUID.randomUUID(),
                stockUpdate.itemId(), stockUpdate.quantity(), stockUpdate.reorderNeeded());
        logger.info("Publishing StockUpdatedEvent for itemId={}", stockUpdate.itemId());
        events.publish(event);
    }
}
