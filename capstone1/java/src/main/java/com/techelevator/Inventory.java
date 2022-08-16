package com.techelevator;


import java.io.File;
import java.math.BigDecimal;
import java.util.*;

public class Inventory {

    private Map<String, VendingItem> vendingItems = new LinkedHashMap<>();
    private Map<VendingItem, Integer> stockInventory = new LinkedHashMap<>();

    public void stockVending() {
        File vendingFile = new File("vendingmachine.csv");
        try (Scanner scanner = new Scanner(vendingFile)) {
            while (scanner.hasNext()) {
                String lineText = scanner.nextLine();
                String[] itemInfo = lineText.split("\\|");
                if (itemInfo[3].equals("Drink")) {
                    vendingItems.put(itemInfo[0],new Beverage(itemInfo[1], new BigDecimal(itemInfo[2]), itemInfo[0]));
                    stockInventory.put(vendingItems.get(itemInfo[0]), 5);
                } else if (itemInfo[3].equals("Candy")) {
                    vendingItems.put(itemInfo[0],new Candy(itemInfo[1], new BigDecimal(itemInfo[2]), itemInfo[0]));
                    stockInventory.put(vendingItems.get(itemInfo[0]), 5);
                } else if (itemInfo[3].equals("Gum")) {
                    vendingItems.put(itemInfo[0],new Gum(itemInfo[1], new BigDecimal(itemInfo[2]), itemInfo[0]));
                    stockInventory.put(vendingItems.get(itemInfo[0]), 5);
                } else if (itemInfo[3].equals("Chip")) {
                    vendingItems.put(itemInfo[0],new Chips(itemInfo[1], new BigDecimal(itemInfo[2]), itemInfo[0]));
                    stockInventory.put(vendingItems.get(itemInfo[0]), 5);
                }
            }
        } catch (Exception ioFile) {
            System.out.println("File not found");
        }
    }
    public void updateInventory(String slotID) {
        int getInventoryValue = stockInventory.get(vendingItems.get(slotID));
        if (vendingItems.containsKey(slotID) && getInventoryValue > 0) {
            stockInventory.replace(vendingItems.get(slotID) , getInventoryValue - 1); ;
            }
        }

    public boolean checkIfSoldOut(String slotID) {
        boolean isSoldOut = false;
        if (vendingItems.containsKey(slotID)) {
            if (stockInventory.get(vendingItems.get(slotID)) == 0) isSoldOut = true;
        }
        return isSoldOut;
    }

    public int getInventoryNumber(String slotID) {
        return stockInventory.get(vendingItems.get(slotID));
    }

    public void getDisplay() {
        System.out.println("\nItem List\n---------------------------");
        for (Map.Entry<String, VendingItem> item: vendingItems.entrySet() ) {
            System.out.println(item.getValue());
        }
}

    public void getMainDisplay() {
        System.out.println("\nItems Available To Buy\n-----------------------");
        for (Map.Entry<VendingItem, Integer> item : stockInventory.entrySet()) {
            if (item.getValue() == 0) {
                System.out.println(String.format("%s| SOLD OUT", item.getKey()));
            }
            else System.out.println(String.format("%s| %d", item.getKey(), item.getValue()));
        }
    }

    public Map<String, VendingItem> getVendingItems() {
        return vendingItems;
    }
}













