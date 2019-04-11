package de.stl.saar.internetentw1.service.interfaces;

import java.util.List;

import de.stl.saar.internetentw1.model.Dish;

public interface DishService {

	public void addDish(final Dish dish);
	
	public void removeDishById(final int dishId);
	
	public List<Dish> findAllDishes();
	
	public Dish findDish(final int dishId);
}
