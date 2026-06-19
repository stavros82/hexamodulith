package com.example.inventory.adapters.messaging;

import com.example.inventory.port.in.ApplyStockUpdateUseCase;
import com.example.sharedkernel.events.StockUpdatedEvent;
import com.example.sharedkernel.outbox.HandleEvent;
import org.springframework.context.event.EventListener;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class StockUpdatedListener {

    private final ApplyStockUpdateUseCase applyStock;

    public StockUpdatedListener(ApplyStockUpdateUseCase applyStock) {
        this.applyStock = applyStock;
    }

    @Async
    @EventListener
    @HandleEvent
    public void on(StockUpdatedEvent event) {

        applyStock.apply(
                event.itemId(),
                event.quantity(),
                event.reorderNeeded()
        );
    }
}
