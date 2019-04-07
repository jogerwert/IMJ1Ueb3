package de.stl.saar.internetentw1.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;

import de.stl.saar.internetentw1.dao.classes.RoleDaoImpl;
import de.stl.saar.internetentw1.dao.classes.UserDaoImpl;
import de.stl.saar.internetentw1.dao.interfaces.RoleDao;
import de.stl.saar.internetentw1.dao.interfaces.UserDao;
import de.stl.saar.internetentw1.i18n.I18nMessageUtil;
import de.stl.saar.internetentw1.model.Role;
import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.utils.JsfUtils;
import de.stl.saar.internetentw1.utils.RandomUtils;

@ManagedBean
@ViewScoped
public class ChangeExistingUserView {
	private User currentUser;
	private User userToChange;
	private List<Role> roleList;
	private String password;
	private String username;
	private String role;
	private List<User> userList;
	private int userID;
	private boolean isPasswordChangeNecessary;
	private UserDao userService;

	
	@PostConstruct
	public void initialize() {
		currentUser = (User)JsfUtils.getBeanAttribute("currentUser", "loginView", User.class);
		userService = (UserDaoImpl)JsfUtils.getBeanAttribute("userService", "loginView", UserDaoImpl.class);

		String selectedUsernameParam = JsfUtils.getParameterByName("selectedUserName");
		userToChange = userService.findUserByName(selectedUsernameParam);
		
		isPasswordChangeNecessary = userToChange.getIsPasswordChangeNecessary();
		password = userToChange.getPassword();
		username = userToChange.getUsername();
		role = userToChange.getRole().toString();
		userID = userToChange.getUserId();
		
		roleList = new RoleDaoImpl().findAllRoles();
		
		userList = userService.findAllUsers();
	}
	
	public void saveChangesToSelectedUser(ActionEvent event) {
		userToChange.setUsername(username);
		userToChange.setPassword(password);
		userToChange.setRole(new RoleDaoImpl().findRoleByName(role));
		userToChange.setIsPasswordChangeNecessary(isPasswordChangeNecessary);
			
	}
	
	
	public void createRandomPassword(ActionEvent event) {
		if(currentUser.isAdmin()) {
			isPasswordChangeNecessary = true;
		}
		password = RandomUtils.createRandomString();
	}

	public void validateUsername(FacesContext facesContext, UIComponent component, Object value)throws ValidatorException {
		String tmpUsername = (String) value;
		User tmpUser = userService.findUserByName(tmpUsername);
		
		if(tmpUser != null) {
			throw new ValidatorException(new FacesMessage(I18nMessageUtil.getErrorUsernameAlreadyExistsString()));		
		}
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
//
//	public String getIsNotAdmin() {
//		return isNotAdmin;
//	}
//
//	public void setIsNotAdmin(String isNotAdmin) {
//		this.isNotAdmin = isNotAdmin;
//	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
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
