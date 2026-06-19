package com.example.outbox.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages ="com.example.outbox.persistence")
@EntityScan(basePackages = "com.example.outbox.persistence")
public class OutboxConfig {
}
