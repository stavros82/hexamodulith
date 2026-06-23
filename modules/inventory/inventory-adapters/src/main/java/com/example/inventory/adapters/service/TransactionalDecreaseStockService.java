package com.example.inventory.adapters.service;



import com.example.inventory.adapters.messaging.InventoryEventPublisher;
import com.example.sharedkernel.events.StockDecreasedEvent;
import com.example.inventory.port.in.DecreaseStockUseCase;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TransactionalDecreaseStockService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionalDecreaseStockService.class);

    private final DecreaseStockUseCase decreaseStockUseCse;
    private final InventoryEventPublisher publisher;

    public TransactionalDecreaseStockService(DecreaseStockUseCase decreaseStockUseCse,
                                             InventoryEventPublisher publisher) {
        this.decreaseStockUseCse = decreaseStockUseCse;
        this.publisher = publisher;
    }

    @Transactional
    public int handle(Integer itemId, int amount) {
        logger.info("Handling stock decrease: itemId={}, amount={}", itemId, amount);
        int newQty = decreaseStockUseCse.handle(itemId, amount);

        StockDecreasedEvent event = new StockDecreasedEvent(UUID.randomUUID(), itemId,  newQty);

        logger.info("Publishing StockDecreasedEvent for itemId={}, newQty={}", itemId, newQty);
        publisher.publish(event);
        return newQty;
    }
}