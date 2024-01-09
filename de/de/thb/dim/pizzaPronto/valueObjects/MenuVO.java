package de.thb.dim.pizzaPronto.valueObjects;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class MenuVO {
	private List<DishVO> dishes;
	
	public MenuVO(ArrayList<DishVO> dishes) {
		this.dishes = dishes;
	}

	/**
	 * Defaultkonstruktor
	 * 
	 */
	public MenuVO() {
		initMenu();
	}

	/**
	 * Method to initialize the menu and create all objects of dishes.
	 * 
	 */
	private void initMenu() {
		this.dishes = new ArrayList<DishVO>();

		dishes.add( new PastaVO(11, "Napoli", new String[] { "Tomatensauce" },
				5.60f, 4));
		dishes.add( new PastaVO(11, "Napoli", new String[] { "Tomatensauce" },
				5.60f, 5));
		dishes.add( new PastaVO(11, "Napoli", new String[] { "Tomatensauce" },
				5.60f, 6));
		dishes.add( new PastaVO(12, "Bolognese",
				new String[] { "Hackfleischsauce" }, 6.40f, 4));
		dishes.add( new PastaVO(12, "Bolognese",
				new String[] { "Hackfleischsauce" }, 6.40f, 5));
		dishes.add( new PastaVO(12, "Bolognese",
				new String[] { "Hackfleischsauce" }, 6.40f, 6));
		dishes.add( new PastaVO(13, "alla Panna", new String[] { "Schinken",
				"Sahne" }, 6.40f, 4));
		dishes.add(new PastaVO(13, "alla Panna", new String[] { "Schinken",
				"Sahne" }, 6.40f, 5));
		dishes.add( new PastaVO(13, "alla Panna", new String[] { "Schinken",
				"Sahne" }, 6.40f, 6));

	}

	/**
	 * Returns the object in human-readable form. Calls for getter of the individual courts. 
	 * Is based on the initialization sequence: pizza, pasta, dessert
	 * @return - complete String
	 * 
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		DecimalFormat dFormat = new DecimalFormat(".00"); //Format the price ...

		sb.append("MENU PIZZA PRONTO\n\n");
		// Pizzas
		int i = 0;

		//Pasta 
		
		sb.append("\nPrima special pastas: \n4  Spaghetti\n5  Tortellini\n6  Gnocchi\n");
		do {
			sb.append(" " + dishes.get(i).getNumber() + "\t");
			sb.append(dishes.get(i).getName() + "\t");

			sb.append(dishes.get(i).ingredientsToString());

			sb.append("\n\tPrice:\t\t\t"
					+ dFormat.format(dishes.get(i).getPrice()) + " Euro");
			if(dishes.get(i).getNumber() == dishes.get(i+1).getNumber() && dishes.get(i).getNumber() == dishes.get(i+2).getNumber()){
				i+=3;
			} else {
			if(dishes.get(i).getNumber() == dishes.get(i+1).getNumber()){
				i+=2;
			}
			else
				i++;
			}
			sb.append("\n");
		} while (i < dishes.size() && dishes.get(i) instanceof PastaVO);

		return sb.toString();
	}

	
	
	// /
	// / Getter und Setter
	// /
	public DishVO getDish(int index) {
			return dishes.get(index);
	}

	public int getNumberOfDishes() {
		return dishes.size();
	}

} 

