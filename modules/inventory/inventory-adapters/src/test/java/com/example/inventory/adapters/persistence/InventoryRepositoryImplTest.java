package com.example.inventory.adapters.persistence;

import com.example.inventory.domain.model.InventoryItem;
import com.example.inventory.port.out.InventoryRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class InventoryRepositoryImplTest {

    private final InventoryJpaRepository jpa = mock(InventoryJpaRepository.class);
    private final InventoryRepository repo = new InventoryRepositoryImpl(jpa);

    @Test
    void saves_item_correctly() {
        InventoryItemEntity entity = new InventoryItemEntity();

        entity.setName("TestItem");
        entity.setQuantity(5);

        when(jpa.save(any())).thenReturn(entity);

        InventoryItem saved = repo.save(new InventoryItem(entity.getItemId(), 5, "TestItem"));

        assertThat(saved.getItemId()).isEqualTo(entity.getItemId());
        assertThat(saved.getName()).isEqualTo("TestItem");
        assertThat(saved.getQuantity()).isEqualTo(5);
    }

    @Test
    void finds_item_by_id() {
        InventoryItemEntity entity = new InventoryItemEntity();

        entity.setName("ItemX");
        entity.setQuantity(10);

        when(jpa.findById(entity.getItemId())).thenReturn(java.util.Optional.of(entity));

        InventoryItem found = repo.findByItemId(entity.getItemId());

        assertThat(found.getItemId()).isEqualTo(entity.getItemId());
        assertThat(found.getName()).isEqualTo("ItemX");
        assertThat(found.getQuantity()).isEqualTo(10);
    }
}
