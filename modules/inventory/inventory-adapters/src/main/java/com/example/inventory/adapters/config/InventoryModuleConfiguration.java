package com.example.inventory.adapters.config;

import com.example.inventory.application.ApplyStockUpdateService;
import com.example.inventory.application.CreateInventoryService;
import com.example.inventory.application.DecreaseStockService;
import com.example.inventory.port.in.ApplyStockUpdateUseCase;
import com.example.inventory.port.in.CreateInventoryUseCase;
import com.example.inventory.port.in.DecreaseStockUseCase;
import com.example.inventory.port.out.InventoryRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.modulith.ApplicationModule;

@Configuration
@EntityScan(basePackages = "com.example")
@EnableJpaRepositories(basePackages = "com.example.inventory.adapters.persistence")
@ApplicationModule(allowedDependencies = {"inventory-core", "outbox-module"})
public class InventoryModuleConfiguration {

    @Bean
    public ApplyStockUpdateUseCase applyStockUpdateUseCase(InventoryRepository repo) {
        return new ApplyStockUpdateService(repo);
    }

    @Bean
    public CreateInventoryUseCase createInventoryService(
          InventoryRepository repo

    ) {
        return new CreateInventoryService(repo);
    }



    @Bean
    public DecreaseStockUseCase decreaseStockService(
          InventoryRepository repo

    ) {
        return new DecreaseStockService(repo);
    }


}
