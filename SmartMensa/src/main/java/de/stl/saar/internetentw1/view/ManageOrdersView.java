package de.stl.saar.internetentw1.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import de.stl.saar.internetentw1.dao.classes.DishDaoImpl;
import de.stl.saar.internetentw1.dao.classes.RoleDaoImpl;
import de.stl.saar.internetentw1.dao.classes.UserDaoImpl;
import de.stl.saar.internetentw1.dao.interfaces.DishDao;
import de.stl.saar.internetentw1.dao.interfaces.UserDao;
import de.stl.saar.internetentw1.model.Dish;
import de.stl.saar.internetentw1.model.Role;
import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.service.interfaces.DishService;
import de.stl.saar.internetentw1.service.interfaces.UserService;
import de.stl.saar.internetentw1.utils.JsfUtils;

@ManagedBean
@ViewScoped
public class ManageOrdersView {
	private User currentUser;
	private UserService userService;
	private DishService dishService;
	private DataModel<Dish> dishDataTable;
	private List<Dish> dishList;
	
	@PostConstruct
	public void initialize() {
		currentUser = JsfUtils.getCurrentUserBeanAttribute();
		userService = JsfUtils.getUserServiceBeanAttribute();
		dishService = JsfUtils.getDishServiceBeanAttribute();
		
		
		dishList = dishService.findAllDishes();
		dishDataTable = new ListDataModel<Dish>();						
		dishDataTable.setWrappedData(dishList);

	}
	
	public void delete(ActionEvent event) {
//		int selectedUserIndex = userDataTable.getRowIndex();
//		User selectedUser = this.userList.get(selectedUserIndex);
//		this.userList.remove(selectedUserIndex);
//		
//		userService.removeUser(selectedUser.getUserId());
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


	



}
