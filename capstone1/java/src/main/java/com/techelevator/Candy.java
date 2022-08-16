package com.techelevator;

import java.math.BigDecimal;

public class Candy extends VendingItem{

    public Candy(String name, BigDecimal price, String slotID) {
        super(name, price, slotID);
    }

    public void getSound() {
        System.out.println("Chew Chew, Yum!");
    }
    public String toString() {
        return String.format("%s|%s|%s|Candy", getSlotID(), getName(), String.valueOf(getPrice()));
    }
}
