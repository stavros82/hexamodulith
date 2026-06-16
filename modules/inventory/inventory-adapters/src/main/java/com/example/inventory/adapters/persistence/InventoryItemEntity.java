package com.example.inventory.adapters.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class InventoryItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;
    private int quantity;
    private String name;

    // Getters and setters
    public int getItemId() {
        return itemId;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
