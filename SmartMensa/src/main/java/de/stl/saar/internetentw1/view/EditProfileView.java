package de.stl.saar.internetentw1.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.h2.util.StringUtils;

import de.stl.saar.internetentw1.dao.classes.RoleDaoImpl;
import de.stl.saar.internetentw1.dao.classes.UserDaoImpl;
import de.stl.saar.internetentw1.dao.interfaces.RoleDao;
import de.stl.saar.internetentw1.dao.interfaces.UserDao;
import de.stl.saar.internetentw1.model.Role;
import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.utils.JsfUtils;

@ManagedBean
@ViewScoped
public class EditProfileView {
	private String selectedUserName;
	private User currentUser;
	private List<Role> roleList;
	private String password;
	private String username;
	private String isAdmin;
	private String isNotAdmin;
	private String role;
	private DataModel<User> userDataTable;
	private List<User> userList;
	private int userID;
	private boolean isPasswordChangeNecessary;
	private UserDao userService;
	
	private User selectedUser;
	
	@PostConstruct
	public void initialize() {
		currentUser = (User)JsfUtils.getBeanAttribute("currentUser", "loginView", User.class);
		userService = (UserDaoImpl)JsfUtils.getBeanAttribute("userService", "loginView", UserDaoImpl.class);
		
		this.selectedUserName = JsfUtils.getParameterByName("selectedUserName");
		if(!StringUtils.isNullOrEmpty(this.selectedUserName)) {
			selectedUser = userService.findUserByName(this.selectedUserName);
		}else {
			selectedUser = currentUser;
		}

		if(currentUser.isAdmin()) {
			isAdmin = "true";
			isNotAdmin = "false";
		}else {
			isAdmin = "false";
			isNotAdmin = "true";
		}
		password = selectedUser.getPassword();
		username = selectedUser.getUsername();
		role = selectedUser.getRole().toString();
		userID = selectedUser.getUserId();
		
		roleList = new RoleDaoImpl().findAllRoles();
		
		userList = userService.findAllUsers();
		userDataTable = new ListDataModel<User>();						
		userDataTable.setWrappedData(userList);
	}
	
	public void saveNewOrExistingUser(ActionEvent event) {
		//Falls Admin
		//pruefe, ob es den User schon in der DB gibt 
		//Lege Ihn an, falls nicht existent, sonst update ihn
		//Falls kein Admin
		//update werte NUR des currentUsers
		//Validierung macht JSF
		User newOrExistingUser = userService.findUserByName(selectedUser.getUsername());
		if(selectedUser.isAdmin() && (selectedUser.getUserId() == currentUser.getUserId())){
			newOrExistingUser = null;
		}
		RoleDao roleService = new RoleDaoImpl();
		Role existingRole = roleService.findRoleByName(role);
		
		int newID = userList.size()+1;
		
		if(currentUser.isAdmin()) {
			if(newOrExistingUser == null) {
				//lege an in db
				newOrExistingUser = new User(newID, username, password, existingRole);
				userService.addUser(newOrExistingUser);
			}else {
				//update den gefundenen user in db
				//userService.removeUser(newOrExistingUser.getUserId());
				newOrExistingUser.setUsername(username);
				newOrExistingUser.setPassword(password);
				newOrExistingUser.setRole(existingRole);
				newOrExistingUser.setIsPasswordChangeNecessary(isPasswordChangeNecessary);
				userService.addUser(newOrExistingUser);
			}
		}else {
			//update currentUser
			currentUser.setUsername(username);
			currentUser.setPassword(password);
			userService.addUser(currentUser);
			//JsfUtils.setValueExpression("loginView", "currentUser.username", User.class, currentUser.getUsername());
		}
			
	}
	

	public String getIsAdmin() {
		return this.isAdmin;
	}
	
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}


	public DataModel<User> getUserDataTable() {
		return userDataTable;
	}

	public void setUserDataTable(DataModel<User> userDataTable) {
		this.userDataTable = userDataTable;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getIsNotAdmin() {
		return isNotAdmin;
	}

	public void setIsNotAdmin(String isNotAdmin) {
		this.isNotAdmin = isNotAdmin;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getSelectedUserName() {
		return selectedUserName;
	}

	public void setSelectedUserName(String selectedUserName) {
		this.selectedUserName = selectedUserName;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public boolean getIsPasswordChangeNecessary() {
		return isPasswordChangeNecessary;
	}

	public void setIsPasswordChangeNecessary(boolean isPasswordChangeNecessary) {
		this.isPasswordChangeNecessary = isPasswordChangeNecessary;
	}


}
