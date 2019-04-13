package de.stl.saar.internetentw1.service.classes;

import java.util.List;

import de.stl.saar.internetentw1.dao.classes.DishDaoImpl;
import de.stl.saar.internetentw1.dao.interfaces.DishDao;
import de.stl.saar.internetentw1.model.Dish;
import de.stl.saar.internetentw1.service.interfaces.DishService;

/**
 * Teil der Service-Schicht. Greift auf DishDao zu.
 * 
 * @author Michelle Blau
 */
public class DishServiceImpl implements DishService{

	private DishDao dishDao;
	
	public DishServiceImpl() {
		this.dishDao = DishDaoImpl.getInstance();
	}
	
	@Override
	public void addDish(Dish dish) {
		this.dishDao.addDish(dish);
		
	}

	@Override
	public void removeDishById(int dishId) {
		this.dishDao.removeDish(dishId);
		
	}

	@Override
	public List<Dish> findAllDishes() {
		return this.dishDao.findAllDishes();
	}
	
	@Override
	public Dish findDish(final int dishId) {
		return  this.dishDao.findDish(dishId);
	}

}
