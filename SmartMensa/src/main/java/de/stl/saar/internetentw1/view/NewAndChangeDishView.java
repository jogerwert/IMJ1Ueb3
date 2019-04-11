package de.stl.saar.internetentw1.view;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import de.stl.saar.internetentw1.i18n.I18nMessageUtil;
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
		this.dishService = new DishServiceImpl();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		this.dishId = facesContext.getExternalContext().getRequestParameterMap().get("dishId");
		
		if(this.dishId == null) {
			this.newDish = true;
		}else {
			this.newDish = false;
			this.dish = dishService.findDish(Integer.parseInt(dishId));
			this.dishName = dish.getDishName();
			this.dishPrice = Double.toString(dish.getPrice());
			this.dishCategory = dish.getCategory().getCategoryName();
			
		}
	}
	
	public void createAndChangeDish(ActionEvent event) {
		if(newDish) {
			createDish();
		}else {
			changeDish();
		}
	}
	
	public void createDish() {
		String name = dishName;
		Double price = Double.parseDouble(dishPrice);
		Category category = Category.getCategoryByName(dishCategory);
		String imageName = "noImageAvailable";
		
		Dish newDish = new Dish(name, price, category, imageName);
		dishService.addDish(newDish);
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						I18nMessageUtil.getDishAddedSummary(),
						I18nMessageUtil.getDishAddedDetail()
						)
				);
		
	}
	
	public void changeDish() {
		dish.setDishName(dishName);
		dish.setPrice(Double.parseDouble(dishPrice));;
		dish.setCategory(Category.getCategoryByName(dishCategory));
		
		dishService.removeDishById(dish.getDishId());
		dishService.addDish(dish);
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, 
						I18nMessageUtil.getDishChangedSummary(), 
						I18nMessageUtil.getDishChangedDetail()
						)
				);
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
