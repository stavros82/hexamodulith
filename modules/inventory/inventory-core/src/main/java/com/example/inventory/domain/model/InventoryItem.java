package com.example.inventory.domain.model;

public class InventoryItem {

    private final Integer itemId;
    private int quantity;
    private String name;
    private boolean reorderNeeded;

    // Full constructor
    public InventoryItem(Integer itemId, int quantity, String name, boolean reorderNeeded) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.name = name;
        this.reorderNeeded = reorderNeeded;
    }

    // Constructor without reorder flag
    public InventoryItem(Integer itemId, int quantity, String name) {
        this(itemId, quantity, name, false);
    }

    // Constructor for new items (name only)
    public InventoryItem(String name) {
        this.itemId = null; // assigned by DB
        this.quantity = 0;
        this.name = name;
        this.reorderNeeded = false;
    }



    // Getters

    public Integer getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public boolean isReorderNeeded() {
        return reorderNeeded;
    }

    // Domain behavior
    public void updateStock(int newQuantity) {
        this.quantity = newQuantity;
    }

    public void markReorderNeeded(boolean needed) {
        this.reorderNeeded = needed;
    }

    public int decrease(int amount) {
        this.quantity -= amount;
        return this.quantity;
    }

    public int increase(int amount) {
        this.quantity += amount;
        return this.quantity;
    }
}
