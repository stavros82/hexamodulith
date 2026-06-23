package com.example.inventory.application;

import com.example.inventory.domain.model.InventoryItem;
import com.example.inventory.port.out.InventoryRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DecreaseStockServiceTest {

    private final InventoryRepository repo = mock(InventoryRepository.class);
    private final DecreaseStockService service = new DecreaseStockService(repo);

    @Test
    void decreasesStockAndSavesItem() {
        Integer itemId = 1;
        InventoryItem item = new InventoryItem(itemId, 20, "Test Item");
        when(repo.findByItemId(itemId)).thenReturn(item);

        int result = service.handle(itemId, 5);

        assert result == 15;
        assert item.getQuantity() == 15;
        verify(repo, times(1)).findByItemId(itemId);
        verify(repo, times(1)).save(item);
    }
}
