package de.stl.saar.internetentw1.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import de.stl.saar.internetentw1.model.Role;
import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.service.classes.RoleServiceImpl;
import de.stl.saar.internetentw1.service.interfaces.RoleService;
import de.stl.saar.internetentw1.service.interfaces.UserService;
import de.stl.saar.internetentw1.utils.JsfUtils;

/**
 * Diese Klasse repraesentiert das Fenster, in dem der Admin andere
 * Benutzer veraendern und entfernen kann.
 * 
 * @author Michelle Blau
 *
 */
@ManagedBean
@ViewScoped
public class ManageUsersView {
	private User currentUser;
	private String selectedUserName;
	private List<Role> roleList;
	private String password;
	private String username;
	private String isAdmin; //wird auch nicht verwendet
	private String isNotAdmin; //wird nicht verwendet
	private String role;
	private DataModel<User> userDataTable;
	private List<User> userList;
	private int userID;
	private UserService userService;
	private RoleService roleService;
	
	/**
	 * Initialisiert u.a. die Tabelle mit den Usern aus der Datenbank
	 */
	@PostConstruct
	public void initialize() {
		currentUser = JsfUtils.getCurrentUserBeanAttribute();
		userService = JsfUtils.getUserServiceBeanAttribute();
		roleService = new RoleServiceImpl();
		
		if(currentUser.isAdmin()) {
			isAdmin = "true";
			isNotAdmin = "false";
		}else {
			isAdmin = "false";
			isNotAdmin = "true";
		}
		password = currentUser.getPassword();
		username = currentUser.getUsername();
		role = currentUser.getRole().toString();
		userID = currentUser.getUserId();
		
		roleList = roleService.findAllRoles();
		
		userList = userService.findAllUsers();
		userDataTable = new ListDataModel<User>();						
		userDataTable.setWrappedData(userList);

	}
	
	/**
	 * Entfernt einen ausgewaehlten User aus der Datenbank
	 * @param event
	 */
	public void delete(ActionEvent event) {
		int selectedUserIndex = userDataTable.getRowIndex();
		User selectedUser = this.userList.get(selectedUserIndex);
		this.userList.remove(selectedUserIndex);
		
		userService.removeUserById(selectedUser.getUserId());
	}
	
	/**
	 * Wechselt bei Klick auf den "Aendern"-Button zur Ansicht zum
	 * Aendern eines Users. Der Username wird als Parameter dorthin
	 * mitgegeben.
	 * @return Dateiname der Seite zum Aendern eines Users
	 */
	public String toChangeExistingUser() {
		return "changeExistingUser";
	}
	
	
	public User getCurrentUser() {
		return this.currentUser;
	}
	
	public void setCurrentUser(User user) {
		this.currentUser = user;
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




}
