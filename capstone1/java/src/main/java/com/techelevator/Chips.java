package com.techelevator;

import java.math.BigDecimal;

public class Chips extends VendingItem{

    public Chips(String name, BigDecimal price, String slotID) {
        super(name, price, slotID);
    }

    public void getSound() {
        System.out.println("Crunch Crunch, Yum!");
    }
    public String toString() {
        return String.format("%s|%s|%s|Chips", getSlotID(), getName(), String.valueOf(getPrice()));
    }
}
