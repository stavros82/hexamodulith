package com.example.stock.adapters.config;

import com.example.stock.application.UpdateStockService;
import com.example.stock.port.in.UpdateStockUseCase;
import com.example.stock.port.out.StockProjectionRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.modulith.ApplicationModule;

@Configuration
@ApplicationModule(allowedDependencies = "stock-core")
@EntityScan(basePackages = "com.example.stock.adapters.persistence")
@EnableJpaRepositories(basePackages = "com.example.stock.adapters.persistence")

public class StockModuleConfiguration {

    @Bean
    public UpdateStockUseCase updateStockUseCase(StockProjectionRepository repo) {
        return new UpdateStockService(repo);
    }
}
