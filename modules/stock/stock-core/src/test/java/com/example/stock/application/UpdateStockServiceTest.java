package com.example.stock.application;

import com.example.stock.domain.StockUpdate;
import com.example.stock.port.out.StockProjectionRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class UpdateStockServiceTest {

    private final StockProjectionRepository repo = mock(StockProjectionRepository.class);
    private final UpdateStockService service = new UpdateStockService(repo);

    @Test
    void callsRepositoryUpdateStock() {
        StockUpdate stockUpdate = new StockUpdate(1, 20, false);

        service.update(stockUpdate);

        verify(repo, times(1)).updateStock(stockUpdate);
    }
}
