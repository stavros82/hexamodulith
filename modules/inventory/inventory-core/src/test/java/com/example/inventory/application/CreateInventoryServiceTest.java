package com.example.inventory.application;

import com.example.inventory.domain.model.InventoryItem;
import com.example.inventory.port.out.InventoryRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class CreateInventoryServiceTest {

    private final InventoryRepository repository = mock(InventoryRepository.class);
    private final CreateInventoryService service = new CreateInventoryService(repository);

    @Test
    void createsItemAndSavesIt() {
        String itemName = "New Item";
        InventoryItem expectedItem = new InventoryItem(itemName);
        when(repository.save(any(InventoryItem.class))).thenReturn(expectedItem);

        InventoryItem result = service.createItem(itemName);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(itemName);
        verify(repository, times(1)).save(any(InventoryItem.class));
    }
}
