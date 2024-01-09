package de.thb.dim.pizzaPronto.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import de.thb.dim.pizzaPronto.businessObjects.Ordering;
import de.thb.dim.pizzaPronto.valueObjects.MenuVO;
import de.thb.dim.pizzaPronto.valueObjects.exceptions.CustomerTooYoungException;
import de.thb.dim.pizzaPronto.valueObjects.CustomerVO;
import de.thb.dim.pizzaPronto.valueObjects.DishVO;

class TestDriverOrdering {

	public static void main(String[] args) {

		MenuVO menu;
		CustomerVO customer1 = null;
		Ordering ordering1;
		Random zufall = new Random();
		List<DishVO> sB;

 
		// Kunde1
		try {
			customer1 = new CustomerVO("Mampf", "Manfred", "", "Essensstra�e", 42,
					LocalDate.of(1990, 6, 28));
		} catch (NullPointerException | CustomerTooYoungException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());

		}
		ordering1 = new Ordering();
		ordering1.startNewOrder(customer1);

		menu = Ordering.getMenu();
		// zuf�llige Testbestellung f�r Kunde1 speichern aus Speisekarte
		for (int i = 0; i < 2; i++) {
			try {
				ordering1.addDish(menu.getDish(zufall
						.nextInt(9)));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());

			}
		}

		System.out.println("\n zuf�llige Testbestellung f�r Kunde1 anzeigen\n");
		sB = ordering1.getCurrentOrder().getShoppingBasket();
		System.out.println(sB);


		// Gesamtpreis Berechnen und auf der Konsole ausgeben
		System.out.println("\n Gesamtpreis Berechnen");
		float totalPrice = ordering1.calculateTotalPrice();
		System.out.println("Gesamtpreis: " + totalPrice + " Euro\n");

		// Methode deleteDish testen

		// Beispielgericht aus dem Warenkorb löschen
		DishVO dishToRemove = sB.get(1); //
		ordering1.deleteDish(dishToRemove);
		System.out.println("Gericht gelöscht: " + dishToRemove);

		// Warenkorb nach dem Löschen auf der Konsole ausgeben
		List<DishVO> updatedShoppingBasket = ordering1.getCurrentOrder().getShoppingBasket();
		System.out.println("Aktualisierter Warenkorb: " + updatedShoppingBasket);

	}
}
