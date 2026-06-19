package com.example.stock.adapters.messaging;

import com.example.sharedkernel.outbox.OutboxEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class StockEventPublisher {
    private final ApplicationEventPublisher events;

    public StockEventPublisher(ApplicationEventPublisher events) {
        this.events = events;
    }

    @OutboxEvent
    public void publish(Object event) {

        events.publishEvent(event);
    }
}