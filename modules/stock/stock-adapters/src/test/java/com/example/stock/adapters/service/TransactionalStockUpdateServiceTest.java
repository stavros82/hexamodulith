package com.example.stock.adapters.service;

import com.example.sharedkernel.events.StockUpdatedEvent;
import com.example.stock.adapters.config.StockTestConfig;
import com.example.stock.adapters.config.StockTestConfig.TestPublisher;
import com.example.stock.domain.StockUpdate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = StockTestConfig.class)
class TransactionalStockUpdateServiceTest {

    @Autowired
    private TransactionalStockUpdateService service;

    @Autowired
    private TestPublisher publisher;

    @Test
    void updates_stock_and_publishes_event() {
        StockUpdate update = new StockUpdate(1, 10, false);

        service.handle(update);

        assertThat(publisher.published).isNotNull();
        assertThat(publisher.published).isInstanceOf(StockUpdatedEvent.class);

        StockUpdatedEvent event = (StockUpdatedEvent) publisher.published;
        assertThat(event.itemId()).isEqualTo(update.itemId());
        assertThat(event.quantity()).isEqualTo(update.quantity());
        assertThat(event.reorderNeeded()).isEqualTo(update.reorderNeeded());
    }
}
