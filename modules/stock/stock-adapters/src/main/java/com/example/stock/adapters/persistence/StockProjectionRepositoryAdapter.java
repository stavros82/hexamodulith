package com.example.stock.adapters.persistence;


import com.example.stock.port.out.StockProjectionRepository;

import org.springframework.stereotype.Repository;

@Repository
public class StockProjectionRepositoryAdapter implements StockProjectionRepository {

    private final StockProjectionJpaRepository jpa;

    public StockProjectionRepositoryAdapter(StockProjectionJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public void updateStockLevel(Integer itemId, int qty) {
        StockProjectionEntity e = jpa.findById(itemId)
                .orElseGet(() -> {
                    StockProjectionEntity ne = new StockProjectionEntity();
                    ne.setItemId(itemId);
                    ne.setQuantity(qty);
                    ne.setReorderNeeded(false);
                    return ne;
                });

        e.setQuantity(qty);
        jpa.save(e);
    }

    @Override
    public void markForReorder(Integer itemId) {
        StockProjectionEntity e = jpa.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found: " + itemId));

        e.setReorderNeeded(true);
        jpa.save(e);
    }
}
