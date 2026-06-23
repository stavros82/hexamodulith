package com.example.stock.adapters.config;

import com.example.stock.adapters.messaging.StockEventPublisher;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "com.example.stock",
        "com.example.outbox",
        "com.example.sharedkernel"
})
@EnableAspectJAutoProxy
public class StockTestConfig {

    @Bean
    public TestPublisher testPublisher() {
        return new TestPublisher();
    }

    @Primary
    @Bean
    public StockEventPublisher publisher(TestPublisher p) {
        return new StockEventPublisher(p);
    }

    public static class TestPublisher implements org.springframework.context.ApplicationEventPublisher {
        public Object published;

        @Override
        public void publishEvent(Object event) {
            this.published = event;
        }
    }
}
