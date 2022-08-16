package com.techelevator;

import java.math.BigDecimal;

public abstract class VendingItem {

    private String name;
    private BigDecimal price;
    private String slotID;

    public VendingItem(String name, BigDecimal price, String slotID) {
        this.name = name;
        this.price = price;
        this.slotID = slotID;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSlotID() {
        return slotID;
    }

    public void changeSlots(String newSlotID) {
        slotID = newSlotID;
    }

    public abstract void getSound();
}
