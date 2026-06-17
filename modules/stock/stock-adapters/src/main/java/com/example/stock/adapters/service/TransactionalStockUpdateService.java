package com.example.stock.adapters.service;


import com.example.sharedkernel.events.StockUpdatedEvent;
import com.example.stock.domain.StockUpdate;
import com.example.stock.port.in.UpdateStockUseCase;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class TransactionalStockUpdateService {

    private final UpdateStockUseCase updateStockUseCase;
    private final ApplicationEventPublisher events;

    TransactionalStockUpdateService(UpdateStockUseCase updateStockUseCase,ApplicationEventPublisher events) {
        this.updateStockUseCase = updateStockUseCase;
        this.events = events;
    }

    @Transactional
    public void handle(StockUpdate stockUpdate) {
        updateStockUseCase.update(stockUpdate);
        events.publishEvent(new StockUpdatedEvent(stockUpdate.itemId(), stockUpdate.quantity(), stockUpdate.reorderNeeded()));
    }
}
