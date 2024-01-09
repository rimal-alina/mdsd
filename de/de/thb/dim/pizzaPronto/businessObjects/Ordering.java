package de.thb.dim.pizzaPronto.businessObjects;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import de.thb.dim.pizzaPronto.controller.IOrdering;
import de.thb.dim.pizzaPronto.valueObjects.DishVO;
import de.thb.dim.pizzaPronto.valueObjects.MenuVO;
import de.thb.dim.pizzaPronto.valueObjects.OrderVO;
import de.thb.dim.pizzaPronto.valueObjects.StateOfOrderVO;
import de.thb.dim.pizzaPronto.valueObjects.CustomerVO;

/**
 * @author schmidt
 *
 */
public class Ordering implements IOrdering {

	private static MenuVO menu;

	private OrderVO currentOrder;
	private CustomerVO currentCustomer;
	private static int nextId = 0;  

	public Ordering() {

		if (menu == null)
			menu = new MenuVO();

		currentOrder = null;
		currentCustomer = null;

	}

	@Override
	public OrderVO startNewOrder(CustomerVO customer) throws NullPointerException{
		if (menu == null)
			menu = new MenuVO();

		Objects.requireNonNull(customer, "Customer must not be null.");

		if (nextId == 0 || nextId / 100000 < LocalDate.now().getYear()) {
			nextId = (LocalDate.now().getYear() * 100000) + 1;
		} else
			nextId++;
		currentOrder = new OrderVO(nextId, StateOfOrderVO.STARTED, LocalDateTime.now(), customer);
		currentCustomer = customer;
		currentCustomer.setOrder(currentOrder);

		return currentOrder;
	}

	@Override
	public void addDish(DishVO dish) throws IllegalStateException {
		if (currentOrder.getState() == StateOfOrderVO.STARTED)
			currentOrder.addDish(dish);
		if (currentOrder.getState() != StateOfOrderVO.STARTED) {
			throw new IllegalStateException("Your order is complete, you can not add any dishes.");
		}
	}

	@Override
	public void deleteDish(DishVO dish) throws IllegalStateException {
		if (currentOrder.getState() == StateOfOrderVO.STARTED)
			currentOrder.deleteDish(dish);

		if (currentOrder.getState() != StateOfOrderVO.STARTED) {
			throw new IllegalStateException("Your order is complete, you can not delete any dishes.");
		}
	}

	@Override
	public float calculateTotalPrice(){
		float price = 0f;
		price = currentOrder.calculatePriceDishes();
		return price;
	}


	/**
	 * @return the currentOrder
	 */
	public OrderVO getCurrentOrder() {
		return currentOrder;
	}

	/**
	 * @param currentOrder the currentOrder to set
	 */
	public void setCurrentOrder(OrderVO currentOrder) {
		this.currentOrder = currentOrder;
	}

	/**
	 * @return the currentCustomer
	 */
	public CustomerVO getCurrentCustomer() {
		return currentCustomer;
	}

	public void setCurrentCustomer(CustomerVO currentCusomer) {
		this.currentCustomer = currentCusomer;
	}

	/**
	 * @return the meno
	 */
	public static MenuVO getMenu() {
		return menu;
	}

	/**
	 * @return the nextId
	 */
	public static int getNextId() {return nextId;}


}
