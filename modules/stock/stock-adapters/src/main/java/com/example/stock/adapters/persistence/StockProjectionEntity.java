package com.example.stock.adapters.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stock_projection")
public class StockProjectionEntity {

    @Id
    private Integer itemId;

    private int quantity;

    private boolean reorderNeeded;

    public Integer getItemId() { return itemId; }
    public void setItemId(Integer itemId) { this.itemId = itemId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public boolean isReorderNeeded() { return reorderNeeded; }
    public void setReorderNeeded(boolean reorderNeeded) { this.reorderNeeded = reorderNeeded; }
}
