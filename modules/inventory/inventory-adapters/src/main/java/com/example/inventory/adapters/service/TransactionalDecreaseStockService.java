package com.example.inventory.adapters.service;



import com.example.sharedkernel.events.StockDecreasedEvent;
import com.example.inventory.port.in.DecreaseStockUseCase;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionalDecreaseStockService {

    private final DecreaseStockUseCase decreaseStockUseCse;
    private final ApplicationEventPublisher publisher;

    public TransactionalDecreaseStockService(DecreaseStockUseCase decreaseStockUseCse,
                                             ApplicationEventPublisher publisher) {
        this.decreaseStockUseCse = decreaseStockUseCse;
        this.publisher = publisher;
    }

    @Transactional
    public int handle(Integer itemId, int amount) {
        int newQty = decreaseStockUseCse.handle(itemId,amount);

        StockDecreasedEvent event = new StockDecreasedEvent( itemId,  newQty);

        publisher.publishEvent(event);
        return newQty;
    }
}