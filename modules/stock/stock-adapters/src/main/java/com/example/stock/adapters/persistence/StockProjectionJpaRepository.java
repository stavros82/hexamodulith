package com.example.stock.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockProjectionJpaRepository
        extends JpaRepository<StockProjectionEntity, Integer> {}
