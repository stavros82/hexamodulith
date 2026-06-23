package com.example.stock.adapters.rest;

import com.example.stock.adapters.service.TransactionalStockUpdateService;
import com.example.stock.domain.StockUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);
    private final TransactionalStockUpdateService transactionalStockUpdateService;


    StockController(TransactionalStockUpdateService transactionalStockUpdateService) {
        this.transactionalStockUpdateService = transactionalStockUpdateService;
    }


    @PostMapping("/{itemId}/update/{quantity}/{reorderNeeded}")
    public void update(
            @PathVariable("itemId") Integer itemId,
            @PathVariable("quantity") int quantity,
            @PathVariable("reorderNeeded") boolean reorderNeeded
    ) {
        logger.info("Stock update request received: itemId={}, quantity={}, reorderNeeded={}",
                itemId, quantity, reorderNeeded);
        StockUpdate stockUpdate = new StockUpdate(itemId, quantity, reorderNeeded);
        transactionalStockUpdateService.handle(stockUpdate);
        logger.info("Stock update request processed for itemId={}", itemId);
    }
}
