package com.example.inventory.adapters.rest;

import com.example.inventory.adapters.service.TransactionalCreateInventoryService;
import com.example.inventory.adapters.service.TransactionalDecreaseStockService;

import com.example.inventory.domain.model.InventoryItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

    private final TransactionalDecreaseStockService decreaseStockService;
    private final TransactionalCreateInventoryService createInventoryService;
    public InventoryController(TransactionalDecreaseStockService decreaseStockService, TransactionalCreateInventoryService createInventoryService) {
        this.decreaseStockService = decreaseStockService;
        this.createInventoryService = createInventoryService;
    }

    @PostMapping("/{itemId}/decrease/{amount}")
    public ResponseEntity<Integer> decrease(@PathVariable("itemId") Integer itemId,
                                            @PathVariable("amount") int amount) {
        logger.info("Decrease request received: itemId={}, amount={}", itemId, amount);
        int newQty = decreaseStockService.handle(itemId, amount);
        logger.info("Decrease processed: itemId={}, newQty={}", itemId, newQty);
        return ResponseEntity.ok(newQty);
    }

    @PostMapping("/create/{name}")
    public ResponseEntity<InventoryItem> createItem(@PathVariable("name") String name) {
        logger.info("Create item request received: name={}", name);
        InventoryItem newItem = createInventoryService.createItem(name);
        logger.info("Item created: id={}, name={}", newItem.getItemId(), name);
        return ResponseEntity.ok(newItem);
    }
}
