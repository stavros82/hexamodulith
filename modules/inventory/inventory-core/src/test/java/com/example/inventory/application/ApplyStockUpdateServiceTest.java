package com.example.inventory.application;

import com.example.inventory.domain.model.InventoryItem;
import com.example.inventory.port.out.InventoryRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class ApplyStockUpdateServiceTest {

    private final InventoryRepository repo = mock(InventoryRepository.class);
    private final ApplyStockUpdateService service = new ApplyStockUpdateService(repo);

    @Test
    void appliesStockUpdateAndSavesItem() {
        Integer itemId = 1;
        InventoryItem item = new InventoryItem(itemId, 10, "Test Item");
        when(repo.findByItemId(itemId)).thenReturn(item);

        service.apply(itemId, 25, true);

        assertThat(item.getQuantity()).isEqualTo(25);
        assertThat(item.isReorderNeeded()).isTrue();
        verify(repo, times(1)).findByItemId(itemId);
        verify(repo, times(1)).save(item);
    }
}
