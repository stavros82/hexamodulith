package com.example.stock.adapters.messaging;

import com.example.sharedkernel.events.ItemCreatedEvent;
import com.example.sharedkernel.events.StockDecreasedEvent;

import com.example.stock.domain.StockUpdate;
import com.example.stock.port.in.UpdateStockUseCase;
import org.springframework.context.event.EventListener;

import org.springframework.stereotype.Component;

@Component
public class InventoryListener {

    private final UpdateStockUseCase projectionService;

    public InventoryListener(UpdateStockUseCase projectionService) {
        this.projectionService = projectionService;
    }
    @EventListener
    public void on(StockDecreasedEvent event) {
        StockUpdate update = new StockUpdate(
                event.itemId(),
                event.newQuantity(),
                event.newQuantity() < 5
        );


        projectionService.update(update);
    }



    @EventListener
    public void on(ItemCreatedEvent event){

        StockUpdate update = new StockUpdate(
                event.itemId(),
                0,
                false);

        projectionService.update(update);
    }
}
