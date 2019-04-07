package de.stl.saar.internetentw1.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import de.stl.saar.internetentw1.dao.classes.RoleDaoImpl;
import de.stl.saar.internetentw1.dao.classes.UserDaoImpl;
import de.stl.saar.internetentw1.dao.interfaces.UserDao;
import de.stl.saar.internetentw1.model.Role;
import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.utils.JsfUtils;

@ManagedBean
@ViewScoped
public class ManageUsersView {
	private User currentUser;
	private String selectedUserName;
	private List<Role> roleList;
	private String password;
	private String username;
	private String isAdmin;
	private String isNotAdmin;
	private String role;
	private DataModel<User> userDataTable;
	private List<User> userList;
	private int userID;
	private UserDao userService;
	
	@PostConstruct
	public void initialize() {
		userService = (UserDaoImpl)JsfUtils.getBeanAttribute("userService", "loginView", UserDaoImpl.class);
		currentUser = (User)JsfUtils.getBeanAttribute("currentUser", "linkView", User.class);
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
		
		roleList = new RoleDaoImpl().findAllRoles();
		
		userList = userService.findAllUsers();
		userDataTable = new ListDataModel<User>();						
		userDataTable.setWrappedData(userList);

	}
	
	public void delete(ActionEvent event) {
		int selectedUserIndex = userDataTable.getRowIndex();
		User selectedUser = this.userList.get(selectedUserIndex);
		this.userList.remove(selectedUserIndex);
		
		userService.removeUser(selectedUser.getUserId());
	}
	
	
	public String toEditSelectedUser() {
//		int selectedUserIndex = userDataTable.getRowIndex();
//		this.selectedUserName = this.userList.get(selectedUserIndex).getUsername();

		return "editProfile";
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
