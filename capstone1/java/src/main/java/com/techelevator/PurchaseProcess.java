package com.techelevator;

import com.techelevator.view.Menu;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PurchaseProcess {
    //public void main (String)

    private BigDecimal balance = BigDecimal.valueOf(0);
    private String PURCHASE_GET_BALANCE = String.format("Current Money Provided: $%s", getBalance());

    private static final String PURCHASE_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_OPTION_FINISH_TRANSACTION = "Finish Transaction";
    private final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_OPTION_FEED_MONEY, PURCHASE_OPTION_SELECT_PRODUCT, PURCHASE_OPTION_FINISH_TRANSACTION, PURCHASE_GET_BALANCE};

    List<String> transactionList = new LinkedList<>();

    public void feedMoney(Scanner input){
        System.out.println("Please insert : $1, $2, $5, $10 only.");
        String moneyInput = input.nextLine();

        if (moneyInput.equals("1") || moneyInput.equals("2") || moneyInput.equals("5") || moneyInput.equals("10")) {
            BigDecimal moneyGiven = new BigDecimal(moneyInput);
            balance = balance.add(moneyGiven);
            addTransaction("Give Change", balance);
        } else {
            System.out.println("Not able to take tender");
        }
    }

    // call inventory for list
    public void selectProduct(Menu menu, Inventory inventory) {
            inventory.getDisplay();

            System.out.println("\nEnter the slot ID that contains the item.");

            String getChoice = menu.getIn().nextLine();

            if (!inventory.getVendingItems().containsKey(getChoice)) {
                System.out.println("Product code does not exist.");
            }
            else if (inventory.checkIfSoldOut(getChoice)) {
                System.out.println("Item is sold out!");
            }
            else {
                VendingItem item = inventory.getVendingItems().get(getChoice);

                if ((balance.subtract(item.getPrice())).compareTo(BigDecimal.ZERO) > 0) {
                    balance = balance.subtract(item.getPrice());
                    System.out.println(String.format("%s %s %s", item.getName(), item.getPrice(), balance));
                    inventory.getVendingItems().get(getChoice).getSound();
                    inventory.updateInventory(getChoice);
                    addTransaction("Give Change", item.getPrice());

                }

                else System.out.println("\nInsufficient funds, please enter more money.");

            }
        }

    public void finishTransaction() {
        BigDecimal nickels = BigDecimal.valueOf(0);
        BigDecimal dimes = BigDecimal.valueOf(0);
        BigDecimal quarters = BigDecimal.valueOf(0);

        if (balance.compareTo(BigDecimal.ZERO) > 0 ) {
            do {
                if (balance.subtract(new BigDecimal(".25")).compareTo(BigDecimal.ZERO) >= 0) {
                    quarters = quarters.add(BigDecimal.ONE);
                    balance = balance.subtract(new BigDecimal(".25"));
                } else if (balance.subtract(new BigDecimal(".10")).compareTo(BigDecimal.ZERO) >= 0) {
                    dimes = dimes.add(BigDecimal.ONE);
                    balance = balance.subtract(new BigDecimal(".10"));
                } else {
                    nickels = nickels.add(BigDecimal.ONE);
                    balance = balance.subtract(new BigDecimal(".05"));
                }
            } while (balance.intValue() > 0);

        }
        System.out.println("\nReturning remaining balance in vending machine...");
        System.out.println(quarters + " quarters, " + dimes + " dimes, " +
                nickels + " nickels given back.");
        addTransaction("Give Change", BigDecimal.valueOf(0))
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String[] getPurchaseMenuOptions() {
        return PURCHASE_MENU_OPTIONS;
    }

    public String currentMoneyProvided() {
        return String.format("Current Money Provided: $%s", getBalance());
    }

    public void addTransaction(String transactionType, BigDecimal moneyAfterTransaction) {
         transactionList.add(String.format("%s %s %s %s", 1, transactionType, balance, moneyAfterTransaction));
    }

}

