package com.example.inventory.adapters.config;

import com.example.inventory.adapters.messaging.InventoryEventPublisher;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement

@ComponentScan(basePackages = {
        "com.example.inventory",
        "com.example.outbox",
        "com.example.sharedkernel"
})

@EnableAspectJAutoProxy
public class InventoryTestConfig {


    @Bean
    public TestPublisher testPublisher() {
        return new TestPublisher();
    }

    @Primary
    @Bean
    public InventoryEventPublisher publisher(TestPublisher p) {
        return new InventoryEventPublisher(p);
    }



    // Service under test: Decrease (if needed)


    // Simple event publisher stub
    public static class TestPublisher implements org.springframework.context.ApplicationEventPublisher {
        public Object published;

        @Override
        public void publishEvent(Object event) {
            this.published = event;
        }
    }
}
