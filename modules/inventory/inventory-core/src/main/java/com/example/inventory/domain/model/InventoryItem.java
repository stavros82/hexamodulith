package com.example.inventory.domain.model;



public class InventoryItem {

    private  Integer itemId;
    private int quantity;
    private  String name;
    public InventoryItem(Integer itemId, int quantity, String name) {
        this.itemId = itemId;
        this.quantity = quantity;

        this.name = name;
    }
    public InventoryItem(Integer itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;


    }

    public InventoryItem(String name) {

        this.quantity = 0;
        this.name = name;
    }




    public Integer itemId() { return itemId; }
    public int quantity() { return quantity; }
    public String getName() { return name; }

    public int decrease(int amount) {
        this.quantity -= amount;
        return this.quantity;
    }

    public int increase(int amount) {
           return this.quantity += amount;
    }
}
