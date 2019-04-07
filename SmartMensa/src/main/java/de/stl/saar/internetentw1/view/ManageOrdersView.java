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
import de.stl.saar.internetentw1.utils.JsfUtils;

@ManagedBean
@ViewScoped
public class ManageOrdersView {
	private User currentUser;
	private UserDao userService;
	private DishDao dishService;
	private DataModel<Dish> dishDataTable;
	private List<Dish> dishList;
	
	@PostConstruct
	public void initialize() {
		userService = (UserDaoImpl)JsfUtils.getBeanAttribute("userService", "loginView", UserDaoImpl.class);
		currentUser = (User)JsfUtils.getBeanAttribute("currentUser", "linkView", User.class);
		dishService = (DishDaoImpl)JsfUtils.getBeanAttribute("dishService", "loginView", DishDaoImpl.class);
		
		
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

	public UserDao getUserService() {
		return userService;
	}

	public void setUserService(UserDao userService) {
		this.userService = userService;
	}

	public DishDao getDishService() {
		return dishService;
	}

	public void setDishService(DishDao dishService) {
		this.dishService = dishService;
	}
	



}
