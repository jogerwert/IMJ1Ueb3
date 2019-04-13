package de.stl.saar.internetentw1.view;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import de.stl.saar.internetentw1.model.Dish;
import de.stl.saar.internetentw1.model.Room;
import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.service.interfaces.DishService;
import de.stl.saar.internetentw1.service.interfaces.UserService;
import de.stl.saar.internetentw1.utils.JsfUtils;

/**
 * Diese Klasse repraesentiert das Fenster, in dem der Benutzer Bestellungen
 * aufgeben kann. 
 * 
 * @author Michelle Blau
 *
 */

@ManagedBean
@ViewScoped
public class ManageOrdersView implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3346910387051947013L;
	private User currentUser;
	private UserService userService;
	private DishService dishService;
	
	private DataModel<Dish> dishDataTable;
	private List<Dish> dishList;
	
	private DataModel<Dish> cartDataTable;
	private List<Dish> cartList;
	private boolean hasCartContent;
	private double totalPrice;
	
	private boolean orderBtnClicked;
	
	private String name;
	private Room room;
	
	/**
	 * Initialisiert u.a. die Tabelle mit den Gerichten und die Tabelle mit dem
	 * Warenkorbinhalt
	 */
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
	
	/**
	 * Fuegt ein Gericht dem Warenkorb hinzu und erhoeht den Gesamtpreis.
	 * Setzt ein Flag, das anzeigt, ob in dem Warenkorb Inhalt ist.
	 * @param event
	 */
	public void addDishToCart(ActionEvent event) {
		int selectedDishIndex = dishDataTable.getRowIndex();
		Dish selectedDish = dishList.get(selectedDishIndex);
		hasCartContent = true;
		
		cartList.add(selectedDish);
		totalPrice += selectedDish.getPrice();
	}
	
	/**
	 * Entfernt ein Gericht aus dem Warenkorb und reduziert den Gesamtpreis.
	 * Setzt ein Flag, das anzeigt, ob in dem Warenkorb noch Inhalt ist.
	 * @param event
	 */
	public void removeDishFromCart(ActionEvent event) {
		int selectedDishIndex = cartDataTable.getRowIndex();
		Dish selectedDish = cartList.get(selectedDishIndex);
		cartList.remove(selectedDishIndex);	
		
		if(cartList.isEmpty()) {
			hasCartContent = false;
		}
		totalPrice -= selectedDish.getPrice();
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

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}


	



}
