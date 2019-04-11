package de.stl.saar.internetentw1.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;

import de.stl.saar.internetentw1.dao.classes.DishDaoImpl;
import de.stl.saar.internetentw1.dao.classes.RoleDaoImpl;
import de.stl.saar.internetentw1.dao.classes.UserDaoImpl;
import de.stl.saar.internetentw1.dao.interfaces.DishDao;
import de.stl.saar.internetentw1.dao.interfaces.UserDao;
import de.stl.saar.internetentw1.i18n.I18nMessageUtil;
import de.stl.saar.internetentw1.model.Dish;
import de.stl.saar.internetentw1.model.Role;
import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.service.interfaces.DishService;
import de.stl.saar.internetentw1.service.interfaces.UserService;
import de.stl.saar.internetentw1.utils.JsfUtils;
import de.stl.saar.internetentw1.utils.StringUtils;

@ManagedBean
@ViewScoped
public class ManageOrdersView {
	private User currentUser;
	private UserService userService;
	private DishService dishService;
	
	private DataModel<Dish> dishDataTable;
	private List<Dish> dishList;
	
	private DataModel<Dish> cartDataTable;
	private List<Dish> cartList;
	private boolean hasCartContent;
	
	private boolean orderBtnClicked;
	
	private String name;
	
	@PostConstruct
	public void initialize() {
		currentUser = JsfUtils.getCurrentUserBeanAttribute();
		userService = JsfUtils.getUserServiceBeanAttribute();
		dishService = JsfUtils.getDishServiceBeanAttribute();
		
		
		dishList = dishService.findAllDishes();
		dishDataTable = new ListDataModel<Dish>();						
		dishDataTable.setWrappedData(dishList);

		cartList = new ArrayList<Dish>();
		cartDataTable = new ListDataModel<Dish>();
		cartDataTable.setWrappedData(cartList);
		hasCartContent = false;
		
		orderBtnClicked = false;
		name = "";
		
	}
	
	public void addDishToCart(ActionEvent event) {
		int selectedDishIndex = dishDataTable.getRowIndex();
		Dish selectedDish = dishList.get(selectedDishIndex);
		hasCartContent = true;
		
		cartList.add(selectedDish);
	}
	
	public void removeDishFromCart(ActionEvent event) {
		int selectedDishIndex = cartDataTable.getRowIndex();
		cartList.remove(selectedDishIndex);	
		
		if(cartList.isEmpty()) {
			hasCartContent = false;
		}
	}
	
	public void showDeliveryData(ActionEvent event) {
		orderBtnClicked = true;
	}
	
	
	
	public User getCurrentUser() {
		return this.currentUser;
	}
	
	public void setCurrentUser(User user) {
		this.currentUser = user;
	}

	public DataModel<Dish> getDishDataTable() {
		return dishDataTable;
	}

	public void setDishDataTable(DataModel<Dish> dishDataTable) {
		this.dishDataTable = dishDataTable;
	}

	public List<Dish> getDishList() {
		return dishList;
	}

	public void setDishList(List<Dish> dishList) {
		this.dishList = dishList;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public DishService getDishService() {
		return dishService;
	}

	public void setDishService(DishService dishService) {
		this.dishService = dishService;
	}

	public DataModel<Dish> getCartDataTable() {
		return cartDataTable;
	}

	public void setCartDataTable(DataModel<Dish> cartDataTable) {
		this.cartDataTable = cartDataTable;
	}

	public List<Dish> getCartList() {
		return cartList;
	}

	public void setCartList(List<Dish> cartList) {
		this.cartList = cartList;
	}

	public boolean getHasCartContent() {
		return hasCartContent;
	}

	public void setHasCartContent(boolean hasCartContent) {
		this.hasCartContent = hasCartContent;
	}

	public boolean getOrderBtnClicked() {
		return orderBtnClicked;
	}

	public void setOrderBtnClicked(boolean orderBtnClicked) {
		this.orderBtnClicked = orderBtnClicked;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	



}