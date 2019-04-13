package de.stl.saar.internetentw1.dao.interfaces;

import java.util.List;

import de.stl.saar.internetentw1.model.Dish;

public interface DishDao {
	/**
	 * Fügt ein neues Gericht in die Datenbank ein. Enthält
	 * der einzutragende Datensatz keinen Primärschlüsselwert
	 * oder ist er bereits vergeben, wird ein noch freier
	 * Primärschlüsselwert eingetragen. 
	 * @param dish Das einzutragende Gericht. 
	 */
	void addDish(Dish dish);
	/**
	 * Loescht einen Datensatz aus der Dish-Tabelle.
	 * @param dishId Die Id des zu löschenden Datensatzes. 
	 */
	void removeDish(int dishId);
	/**
	 * Ermittelt alle Gerichte in der Datenbank. 
	 * @return Eine Liste mit allen Gerichten in der Dish-Tabelle. Eine leere Liste,
	 * falls die Tabelle leer ist. 
	 */
	List<Dish> findAllDishes();
	
	/**
	 * Ermittelt ein spezifisches Gericht in der Datenbank.
	 * @param dishId Die Id des gesuchten Gerichtes.
	 * @return Das gesuchte Gericht.
	 */
	Dish findDish(int dishId);

}
