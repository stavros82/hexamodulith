package com.example.stock.adapters.messaging;


import com.example.sharedkernel.events.ItemCreatedEvent;
import com.example.sharedkernel.events.StockDecreasedEvent;

import com.example.stock.application.StockProjectionService;
import org.springframework.context.event.EventListener;

import org.springframework.stereotype.Component;

@Component
public class StockListener {

    private final StockProjectionService projectionService;

    public StockListener(StockProjectionService projectionService) {
        this.projectionService = projectionService;
    }

    @EventListener
    public void on(StockDecreasedEvent event) {

        System.out.println("Stock module received event: " + event.itemId());

        // 2. Update stock projection
        projectionService.updateProjection(event.itemId(), event.newQuantity());

        // 3. Trigger reorder logic
        if (event.newQuantity() < 5) {
            projectionService.triggerReorder(event.itemId(), event.newQuantity());
        }
    }

    @EventListener
    public void on(ItemCreatedEvent event){
        System.out.println("Stock module received event: " + event.itemId());

        // 2. Update stock projection
        projectionService.updateProjection(event.itemId(), 0);

    }
}
