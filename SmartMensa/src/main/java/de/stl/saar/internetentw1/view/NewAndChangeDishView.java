package de.stl.saar.internetentw1.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;

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
	
	public void validateDishName(FacesContext facesContext, UIComponent component, Object value)throws ValidatorException{
		String tempName = (String) value;
		
		if(tempName.isEmpty()) {
			throw new ValidatorException(new FacesMessage(I18nMessageUtil.getDishChangeErrorNameEmpty()));
		} else if(tempName.trim().isEmpty()) {
			throw new ValidatorException(new FacesMessage(I18nMessageUtil.getDishChangeErrorNameEmpty()));
		}
	}
	
	public void validateDishPrice(FacesContext facesContext, UIComponent component, Object value)throws ValidatorException{
		String tempPriceString = (String) value;
		
		if(tempPriceString == null) {
			throw new ValidatorException(new FacesMessage(I18nMessageUtil.getDishChangeErrorPriceEmpty()));
		}else if(tempPriceString.trim().isEmpty()) {
			throw new ValidatorException(new FacesMessage(I18nMessageUtil.getDishChangeErrorPriceEmpty()));
		} else {
			// Eine Zahl bestehend aus mindestens einer Zahl, ein Punkt und mindestens eine weitere Zahl 
			// folgen koennen.
			String regex = "\\d+(\\.{1}\\d+)?";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher((CharSequence) tempPriceString);
			
			if(!matcher.matches()) {
				throw new ValidatorException(new FacesMessage(I18nMessageUtil.getDishChangeErrorPriceNotANumber()));
			} else {
				double tempPrice = Double.parseDouble(tempPriceString);
				
				if(tempPrice < 0.5 || tempPrice > 10.0) {
					throw new ValidatorException(new FacesMessage(I18nMessageUtil.getDishChangeErrorPriceWrong()));
				}
			}
		}
		
		
	}
	
	public void validateDishCategory(FacesContext facesContext, UIComponent component, Object value)throws ValidatorException{
		String tempCategoryString = (String) value;
		
		if(tempCategoryString == null) {
			throw new ValidatorException(new FacesMessage(I18nMessageUtil.getDishChangeErrorCategoryEmpty()));
		}
	}

}
