package de.stl.saar.internetentw1.view;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import de.stl.saar.internetentw1.model.Category;
import de.stl.saar.internetentw1.model.Dish;
import de.stl.saar.internetentw1.service.classes.DishServiceImpl;
import de.stl.saar.internetentw1.service.interfaces.DishService;

@ManagedBean
@ViewScoped
public class NewAndChangeDishView {
	
	private String dishId;
	private String dishName;
	private String dishPrice;
	private String dishCategory;
	private Dish dish;
	private DishService dishService;
	private boolean newDish;
	
	@PostConstruct
	public void init() {
		dishService = new DishServiceImpl();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		dishId = facesContext.getExternalContext().getRequestParameterMap().get("dishId");
		
		if(dishId == null) {
			this.newDish = true;
		}else {
			this.newDish = false;
			this.dish = dishService.findDish(Integer.parseInt(dishId));
			
		}
	}

	public String getDishId() {
		return dishId;
	}

	public void setDishId(String dishId) {
		this.dishId = dishId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getDishPrice() {
		return dishPrice;
	}

	public void setDishPrice(String dishPrice) {
		this.dishPrice = dishPrice;
	}

	public String getDishCategory() {
		return dishCategory;
	}

	public void setDishCategory(String dishCategory) {
		this.dishCategory = dishCategory;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

}
