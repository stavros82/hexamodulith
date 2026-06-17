package com.example.stock.adapters.persistence;

import com.example.stock.domain.StockUpdate;
import com.example.stock.port.out.StockProjectionRepository;
import org.springframework.stereotype.Component;

@Component
public class StockProjectionRepositoryAdapter implements StockProjectionRepository {

    private final StockProjectionJpaRepository jpa;

    public StockProjectionRepositoryAdapter(StockProjectionJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public void updateStock(StockUpdate stockUpdate) {
        StockProjectionEntity entity =
                jpa.findById(stockUpdate.itemId()).orElseGet(() -> {
                    StockProjectionEntity e = new StockProjectionEntity();
                    e.setItemId(stockUpdate.itemId());
                    e.setQuantity(stockUpdate.quantity());
                    e.setReorderNeeded(stockUpdate.reorderNeeded());
                    return e;
                });

        entity.setQuantity(stockUpdate.quantity());
        entity.setReorderNeeded(stockUpdate.reorderNeeded());

        jpa.save(entity);
    }
}
