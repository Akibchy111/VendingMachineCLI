package com.techelevator;

import java.math.BigDecimal;

public class Beverage extends VendingItem{

    public Beverage(String name, BigDecimal price, String slotID) {
        super(name, price, slotID);
    }

    public void getSound() {
        System.out.println("Glug Glug, Yum!");
    }
    public String toString() {
        return String.format("%s|%s|%s|Beverage", getSlotID(), getName(), getPrice());
    }
}
