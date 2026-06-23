package com.example.stock.adapters.persistence;

import com.example.stock.domain.StockUpdate;
import com.example.stock.port.out.StockProjectionRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StockProjectionRepositoryAdapterTest {

    private final StockProjectionJpaRepository jpa = mock(StockProjectionJpaRepository.class);
    private final StockProjectionRepository repo = new StockProjectionRepositoryAdapter(jpa);

    @Test
    void updates_existing_stock_correctly() {
        Integer itemId = 1;
        StockProjectionEntity existingEntity = new StockProjectionEntity();
        existingEntity.setItemId(itemId);
        existingEntity.setQuantity(10);
        existingEntity.setReorderNeeded(false);

        when(jpa.findById(itemId)).thenReturn(java.util.Optional.of(existingEntity));

        StockUpdate update = new StockUpdate(itemId, 15, true);
        repo.updateStock(update);

        verify(jpa, times(1)).save(existingEntity);
        assert existingEntity.getQuantity() == 15;
        assert existingEntity.isReorderNeeded();
    }

    @Test
    void creates_new_stock_projection_if_not_exists() {
        Integer itemId = 2;
        when(jpa.findById(itemId)).thenReturn(java.util.Optional.empty());

        StockUpdate update = new StockUpdate(itemId, 5, false);
        repo.updateStock(update);

        verify(jpa, times(1)).save(any(StockProjectionEntity.class));
    }
}
