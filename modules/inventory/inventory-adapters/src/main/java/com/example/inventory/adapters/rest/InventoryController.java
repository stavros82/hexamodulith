package com.example.inventory.adapters.rest;

import com.example.inventory.adapters.service.TransactionalCreateInventoryService;
import com.example.inventory.adapters.service.TransactionalDecreaseStockService;

import com.example.inventory.domain.model.InventoryItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final TransactionalDecreaseStockService decreaseStockService;
    private final TransactionalCreateInventoryService createInventoryService;
    public InventoryController(TransactionalDecreaseStockService decreaseStockService, TransactionalCreateInventoryService createInventoryService) {
        this.decreaseStockService = decreaseStockService;
        this.createInventoryService = createInventoryService;
    }

    @PostMapping("/{itemId}/decrease/{amount}")
    public ResponseEntity<Integer> decrease(
            @PathVariable("itemId") Integer itemId,
            @PathVariable("amount") int amount
    ) {
        int newQty = decreaseStockService.handle(itemId, amount);
        return ResponseEntity.ok(newQty);
    }

    @PostMapping("/create/{name}")
    public ResponseEntity<InventoryItem> createItem(
            @PathVariable("name")  String name) {
        InventoryItem newItem = createInventoryService.createItem(name);
        return ResponseEntity.ok(newItem);
    }
}
