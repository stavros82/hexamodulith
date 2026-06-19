package com.example.inventory.adapters.messaging;

import com.example.sharedkernel.outbox.OutboxEvent;
import org.springframework.context.ApplicationEventPublisher;

import org.springframework.stereotype.Component;

@Component
public class InventoryEventPublisher {
    private final ApplicationEventPublisher events;

    public InventoryEventPublisher(ApplicationEventPublisher events) {
        this.events = events;
    }


    @OutboxEvent
    public void publish(Object event) {

        events.publishEvent(event);
    }
}