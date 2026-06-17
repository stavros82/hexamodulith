package com.example.stock.adapters.rest;


import com.example.stock.adapters.service.TransactionalStockUpdateService;
import com.example.stock.domain.StockUpdate;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {

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
       StockUpdate stockUpdate = new StockUpdate(itemId, quantity, reorderNeeded);


        transactionalStockUpdateService.handle(stockUpdate);


    }
}
