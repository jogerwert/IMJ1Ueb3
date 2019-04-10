package de.stl.saar.internetentw1.service.classes;

import java.util.List;

import de.stl.saar.internetentw1.dao.classes.DishDaoImpl;
import de.stl.saar.internetentw1.dao.interfaces.DishDao;
import de.stl.saar.internetentw1.model.Dish;
import de.stl.saar.internetentw1.service.interfaces.DishService;

public class DishServiceImpl implements DishService{

	private DishDao dishDao;
	
	public DishServiceImpl() {
		this.dishDao = new DishDaoImpl();
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

}
