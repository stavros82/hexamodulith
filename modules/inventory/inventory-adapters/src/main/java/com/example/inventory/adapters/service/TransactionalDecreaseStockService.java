package com.example.inventory.adapters.service;



import com.example.inventory.adapters.messaging.InventoryEventPublisher;
import com.example.sharedkernel.events.StockDecreasedEvent;
import com.example.inventory.port.in.DecreaseStockUseCase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TransactionalDecreaseStockService {

    private final DecreaseStockUseCase decreaseStockUseCse;
    private final InventoryEventPublisher publisher;

    public TransactionalDecreaseStockService(DecreaseStockUseCase decreaseStockUseCse,
                                             InventoryEventPublisher publisher) {
        this.decreaseStockUseCse = decreaseStockUseCse;
        this.publisher = publisher;
    }

    @Transactional
    public int handle(Integer itemId, int amount) {
        int newQty = decreaseStockUseCse.handle(itemId,amount);

        StockDecreasedEvent event = new StockDecreasedEvent(UUID.randomUUID(), itemId,  newQty);

        publisher.publish(event);
        return newQty;
    }
}