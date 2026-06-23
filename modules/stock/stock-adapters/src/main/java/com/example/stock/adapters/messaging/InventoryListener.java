package com.example.stock.adapters.messaging;

import com.example.sharedkernel.events.ItemCreatedEvent;
import com.example.sharedkernel.events.StockDecreasedEvent;

import com.example.sharedkernel.outbox.HandleEvent;
import com.example.stock.domain.StockUpdate;
import com.example.stock.port.in.UpdateStockUseCase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
public class InventoryListener {

    private final UpdateStockUseCase projectionService;

    @Value("${outbox.test-failure:false}")
    private boolean testFailure;

    public InventoryListener(UpdateStockUseCase projectionService) {

        this.projectionService = projectionService;
    }


    @Async
    @EventListener
    @HandleEvent
    public void on(StockDecreasedEvent event) {

        StockUpdate update = new StockUpdate(
                event.itemId(),
                event.newQuantity(),
                event.newQuantity() < 5
        );


        projectionService.update(update);
    }





    @Async
    @EventListener
    @HandleEvent
    public void on(ItemCreatedEvent event){

        if (testFailure) {
            throw new RuntimeException("Simulated timeout for Postman test");
        }

        StockUpdate update = new StockUpdate(
                event.itemId(),
                0,
                false);

        projectionService.update(update);
    }
}
