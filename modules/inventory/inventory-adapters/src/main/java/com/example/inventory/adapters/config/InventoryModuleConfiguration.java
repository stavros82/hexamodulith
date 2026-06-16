package com.example.inventory.adapters.config;

import com.example.inventory.application.CreateInventoryUseCase;
import com.example.inventory.application.DecreaseStockUseCase;
import com.example.inventory.port.out.InventoryRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.modulith.ApplicationModule;

@Configuration
@EntityScan(basePackages = "com.example.inventory.adapters.persistence")
@EnableJpaRepositories(basePackages = "com.example.inventory.adapters.persistence")
@ApplicationModule(allowedDependencies = "inventory-core")
public class InventoryModuleConfiguration {
    @Bean
    public CreateInventoryUseCase createInventoryService(
          InventoryRepository repo

    ) {
        return new CreateInventoryUseCase(repo);
    }



    @Bean
    public DecreaseStockUseCase decreaseStockService(
          InventoryRepository repo

    ) {
        return new DecreaseStockUseCase(repo);
    }


}
