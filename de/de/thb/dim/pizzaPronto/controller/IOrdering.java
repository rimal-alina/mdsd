package de.thb.dim.pizzaPronto.controller;

import de.thb.dim.pizzaPronto.valueObjects.CustomerVO;
import de.thb.dim.pizzaPronto.valueObjects.DishVO;
import de.thb.dim.pizzaPronto.valueObjects.OrderVO;


public interface IOrdering {
	
	public OrderVO startNewOrder(CustomerVO customer) throws NullPointerException;
	
	public void addDish(DishVO dish) throws IllegalStateException;
	
	public void deleteDish(DishVO dish) throws IllegalStateException;
	
	public float calculateTotalPrice();

}
