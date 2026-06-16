package com.example.inventory.adapters.service;


import com.example.inventory.application.DecreaseStockUseCase;
import com.example.inventory.domain.event.StockDecreasedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionalDecreaseStockUseCaeService {

    private final DecreaseStockUseCase decreaseStockUseCase;
    private final ApplicationEventPublisher publisher;

    public TransactionalDecreaseStockUseCaeService(DecreaseStockUseCase decreaseStockUseCase,
                                                   ApplicationEventPublisher publisher) {
        this.decreaseStockUseCase = decreaseStockUseCase;
        this.publisher = publisher;
    }

    @Transactional
    public int handle(Integer itemId, int amount) {
        int newQty = decreaseStockUseCase.handle(itemId,amount);

        StockDecreasedEvent event = new StockDecreasedEvent( itemId,  newQty);

        publisher.publishEvent(event);
        return newQty;
    }
}