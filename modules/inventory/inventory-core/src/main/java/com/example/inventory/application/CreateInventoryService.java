
package com.example.inventory.application;


import com.example.inventory.domain.model.InventoryItem;
import com.example.inventory.port.in.CreateInventoryUseCase;
import com.example.inventory.port.out.InventoryRepository;



public class CreateInventoryService implements CreateInventoryUseCase {
    private final InventoryRepository repository;


    public CreateInventoryService(InventoryRepository repository) {
        this.repository = repository;

    }


    @Override
     public InventoryItem createItem(String name) {

      return repository.save(new InventoryItem(name));


    }
}
