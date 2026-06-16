package com.example.inventory.adapters.service;

import com.example.inventory.domain.event.ItemCreatedEvent;
import com.example.inventory.domain.model.InventoryItem;
import com.example.inventory.application.CreateInventoryUseCase;
import com.example.inventory.port.out.InventoryRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionalCreateInventoryService {

    private final CreateInventoryUseCase createUseCase;
    private final ApplicationEventPublisher publisher;

    public TransactionalCreateInventoryService(InventoryRepository repo,
                                                 ApplicationEventPublisher publisher) {
        this.createUseCase = new CreateInventoryUseCase(repo);
        this.publisher = publisher;
    }

    @Transactional
    public InventoryItem createItem(String name) {
        InventoryItem item = createUseCase.createItem(name);

        ItemCreatedEvent event = new ItemCreatedEvent(
                String.valueOf(item.itemId()),
                item.getName()
        );

        publisher.publishEvent(event);
        return item;
    }
}