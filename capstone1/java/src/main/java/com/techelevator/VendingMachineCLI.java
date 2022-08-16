package com.techelevator;

import com.techelevator.view.Menu;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		boolean vendingMachineOn = true;
		Inventory newInventory = new Inventory();
		newInventory.stockVending();

		while (vendingMachineOn) {
			PurchaseProcess purchaseHistory = new PurchaseProcess();

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				newInventory.getMainDisplay();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				boolean stillHungry = true;
				while (stillHungry) {
					String[] updatedPurchaseOptions = purchaseHistory.getPurchaseMenuOptions();
					String choice1 = (String) menu.getChoiceFromOptions(updatedPurchaseOptions);

					if (choice1.equals(purchaseHistory.getPurchaseMenuOptions()[0])) {
						purchaseHistory.feedMoney(menu.getIn());
						updatedPurchaseOptions[3] = purchaseHistory.currentMoneyProvided();
					}
					else if (choice1.equals(purchaseHistory.getPurchaseMenuOptions()[1])) {
						purchaseHistory.selectProduct(menu, newInventory);
						updatedPurchaseOptions[3] = purchaseHistory.currentMoneyProvided();
					}
					else if (choice1.equals(purchaseHistory.getPurchaseMenuOptions()[2])) {
						purchaseHistory.finishTransaction();
						purchaseHistory.addTransaction("Give Change", BigDecimal.valueOf(0));
						stillHungry = false;
					}
				}
			}
			else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				vendingMachineOn = false;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);

		cli.run();
	}
}
