package de.thb.dim.pizzaPronto.controller;

import java.time.LocalDate;

import java.util.Random;

import de.thb.dim.pizzaPronto.businessObjects.Ordering;
import de.thb.dim.pizzaPronto.valueObjects.exceptions.CustomerTooYoungException;
import de.thb.dim.pizzaPronto.valueObjects.CustomerVO;

class TestDriver {

	public static void main(String[] args) {

		CustomerVO customer1 = null;
		Ordering ordering1;
		Random zufall = new Random();


		// Kunde1
		try {
			customer1 = new CustomerVO("Mampf", "Manfred","", "Essensstra�e", 42,
					LocalDate.of(1990, 6, 28));
		} catch (NullPointerException | CustomerTooYoungException e1) {
			System.err.println(e1.getMessage());
		}
		ordering1 = new Ordering();
		
		try {
		ordering1.startNewOrder(customer1);
		} catch (NullPointerException  e1) {
			System.err.println(e1.getMessage());
		}

		System.out.println(customer1);

	}
}
