package com.example.inventory.adapters.service;

import com.example.inventory.adapters.messaging.InventoryEventPublisher;
import com.example.sharedkernel.events.ItemCreatedEvent;
import com.example.inventory.domain.model.InventoryItem;
import com.example.inventory.application.CreateInventoryService;
import com.example.inventory.port.in.CreateInventoryUseCase;
import com.example.inventory.port.out.InventoryRepository;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TransactionalCreateInventoryService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionalCreateInventoryService.class);
    private final CreateInventoryUseCase createUseCase;
    private final InventoryEventPublisher publisher;

    public TransactionalCreateInventoryService(InventoryRepository repo,
                                               InventoryEventPublisher publisher) {
        this.createUseCase = new CreateInventoryService(repo);
        this.publisher = publisher;
    }

    @Transactional
    public InventoryItem createItem(String name) {
        logger.info("Creating inventory item with name={}", name);
        InventoryItem item = createUseCase.createItem(name);

        ItemCreatedEvent event = new ItemCreatedEvent(UUID.randomUUID(),
                item.getItemId()
        );
        logger.info("Publishing ItemCreatedEvent for itemId={}", item.getItemId());
        publisher.publish(event);
        return item;
    }
}