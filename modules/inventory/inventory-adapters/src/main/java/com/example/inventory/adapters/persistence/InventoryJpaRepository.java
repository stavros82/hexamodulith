package com.example.inventory.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryJpaRepository extends JpaRepository<InventoryItemEntity, Integer> {}

