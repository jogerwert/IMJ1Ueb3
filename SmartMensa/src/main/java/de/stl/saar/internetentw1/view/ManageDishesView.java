package de.stl.saar.internetentw1.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import de.stl.saar.internetentw1.model.Dish;
import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.service.classes.DishServiceImpl;
import de.stl.saar.internetentw1.service.interfaces.DishService;
import de.stl.saar.internetentw1.utils.JsfUtils;

@ManagedBean
@ViewScoped
public class ManageDishesView {
	private DishService dishService;
	private User currentUser;
	private DataModel<Dish> dishesList;
	
	@PostConstruct
	public void initialize() {
		dishService = new DishServiceImpl();
		currentUser = (User) JsfUtils.getBeanAttribute("currentUser", "loginView", User.class);
		List<Dish> dishes = dishService.findAllDishes();
		dishesList = new ListDataModel<Dish>();
		dishesList.setWrappedData(dishes);
	}
	
	public void removeDish(ActionEvent event) {
		Dish selectedDish = dishesList.getRowData();
		dishService.removeDishById(selectedDish.getDishId());
	}

	public DataModel<Dish> getDishesList() {
		return dishesList;
	}

	public void setDishesList(DataModel<Dish> dishesList) {
		this.dishesList = dishesList;
	}

	public User getCurrentUser() {
		return currentUser;
	}

}
