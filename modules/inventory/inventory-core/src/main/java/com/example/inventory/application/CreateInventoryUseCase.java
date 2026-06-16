
package com.example.inventory.application;


import com.example.inventory.domain.model.InventoryItem;
import com.example.inventory.port.out.InventoryRepository;



public class CreateInventoryUseCase {
    private final InventoryRepository repository;


    public CreateInventoryUseCase(InventoryRepository repository) {
        this.repository = repository;

    }

    public InventoryItem createItem(String name) {

      return repository.save(new InventoryItem(name));


    }
}
