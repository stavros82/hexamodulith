package com.example.inventory.adapters.service;

import com.example.inventory.adapters.config.InventoryTestConfig;
import com.example.inventory.adapters.config.InventoryTestConfig.TestPublisher;
import com.example.inventory.domain.model.InventoryItem;
import com.example.sharedkernel.events.ItemCreatedEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = InventoryTestConfig.class)
class CreateInventoryServiceTest {

    @Autowired
    private TransactionalCreateInventoryService service;

    @Autowired
    private TestPublisher publisher;

    @Test
    void creates_item_and_publishes_event() {
        // Act
        InventoryItem item = service.createItem("TestItem");

        // Assert domain logic
        assertThat(item).isNotNull();
        assertThat(item.getName()).isEqualTo("TestItem");

        // Assert event publishing
        assertThat(publisher.published).isNotNull();
        assertThat(publisher.published).isInstanceOf(ItemCreatedEvent.class);

        ItemCreatedEvent event = (ItemCreatedEvent) publisher.published;

        assertThat(event.itemId()).isEqualTo(item.getItemId());
    }
}
