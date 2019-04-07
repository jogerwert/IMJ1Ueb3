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
public class EditProfileView {
	private User currentUser;
	private List<Role> roleList;
	private String password;
	private String username;
	private String isAdmin;
	private String isNotAdmin;
	private String role;
	private List<User> userList;
	private int userID;
	private boolean isPasswordChangeNecessary;
	private UserDao userService;

	
	@PostConstruct
	public void initialize() {
		currentUser = (User)JsfUtils.getBeanAttribute("currentUser", "loginView", User.class);
		userService = (UserDaoImpl)JsfUtils.getBeanAttribute("userService", "loginView", UserDaoImpl.class);

		if(currentUser.isAdmin()) {
			isAdmin = "true";
			isNotAdmin = "false";
		}else {
			isAdmin = "false";
			isNotAdmin = "true";
		}
		isPasswordChangeNecessary = currentUser.getIsPasswordChangeNecessary();
		password = currentUser.getPassword();
		username = currentUser.getUsername();
		role = currentUser.getRole().toString();
		userID = currentUser.getUserId();
		
		roleList = new RoleDaoImpl().findAllRoles();
		
		userList = userService.findAllUsers();
	}
	
	public void saveChangesToCurrentUser(ActionEvent event) {
		if(currentUser.isAdmin()) {
			currentUser.setUsername(username);
			currentUser.setPassword(password);
			currentUser.setRole(new RoleDaoImpl().findRoleByName(role));
			currentUser.setIsPasswordChangeNecessary(isPasswordChangeNecessary);
		}else {
			currentUser.setUsername(username);
			currentUser.setPassword(password);
		}
			
	}
	
	public void createNewUser(ActionEvent event) {
		if(currentUser.isAdmin()) {
			Role selectedRole = new RoleDaoImpl().findRoleByName(role);
			User newUser = new User(0, username, password, selectedRole );
			newUser.setIsPasswordChangeNecessary(isPasswordChangeNecessary);
			userService.addUser(newUser);
		}	
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
