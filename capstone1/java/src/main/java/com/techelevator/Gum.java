package com.techelevator;

import java.math.BigDecimal;

public class Gum extends VendingItem{

    public Gum(String name, BigDecimal price, String slotID) {
        super(name, price, slotID);
    }

    public void getSound() {
        System.out.println("Munch Munch, Yum!");
    }
    public String toString() {
        return String.format("%s|%s|%s|Gum", getSlotID(), getName(), String.valueOf(getPrice()));
    }
}
