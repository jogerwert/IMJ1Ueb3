package de.stl.saar.internetentw1.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.validator.ValidatorException;

import de.stl.saar.internetentw1.dao.classes.*;
import de.stl.saar.internetentw1.dao.interfaces.*;
import de.stl.saar.internetentw1.i18n.I18nMessageUtil;
import de.stl.saar.internetentw1.model.*;
import de.stl.saar.internetentw1.utils.StringUtils;
import de.stl.saar.internetentw1.constants.*;


/**
 * Diese Klasse repraesentiert das Fenster, in welchem der Benutzer sich ins Spiel einloggen kann.
 * @author Christopher
 *
 */

@ManagedBean
@SessionScoped
public class LoginView {
	private UserDao userService;
	private DishDao dishService;
	private String username;
	private String password;
	private User currentUser;	
	private String selectedUserName;
	
	@PostConstruct
	public void initializeBean() {
		userService = new UserDaoImpl();
		dishService = new DishDaoImpl();
	}
	
	public void initialize(ComponentSystemEvent event) {
		username = "";
		password = "";
	}
	
	public String toRegisterPage() {
		return "register";
	}
	
	public boolean isUserLoggedIn() {
		if (currentUser != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isUserAdmin() {
		if(isUserLoggedIn()) {
			if(currentUser.getRole().getRoleName().equals("admin")) {
				return true;
			}
		}
		return false;
	}
	
	public String login() {
		List<User> userList = this.userService.findAllUsers();
		for (final User user: userList) {
			if (user.getUsername().equals(username)) {
				if (user.getPassword().equals(password)) {
					currentUser = user; 
					if(currentUser.getIsPasswordChangeNecessary()) {
						return "changePassword";
					}else {
						return "overview";
					}
						
				}
			}
		}
		return null;
	}
	
//	public void logOut(ActionEvent actionEvent) {
//		currentUser = null;
//	}
	
	public String logout() {
		currentUser = null;
		
		return "login";
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	
	
	public String getSelectedUserName() {
		return selectedUserName;
	}

	public void setSelectedUserName(String selectedUserName) {
		this.selectedUserName = selectedUserName;
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

	public String getUrlIngameLinks() {
		if (isUserLoggedIn()) {
			return UrlConstants.USER_LINKS;
		} else {
			return UrlConstants.ADMIN_LINKS;
		}
	}
	
	public void validatePassword(FacesContext facesContext, UIComponent component, Object value)throws ValidatorException {
		String tmpPassword = (String) value;
		User tmpUser = null;
//		
//		for (User u : userList) {
//			if(StringUtils.areStringsEqual(u.getPassword(), tmpPassword)) {
//				tmpUser = u;
//			}
//		}
//		
		List<User> userList = this.userService.findAllUsers();
		for(User u : userList) {
			if(u.getUsername().equals(this.username)) {
				if(u.getPassword().equals(tmpPassword)) {
					tmpUser = u;
				}
			}
		}
		
		if(tmpUser == null) {
			throw new ValidatorException(new FacesMessage(I18nMessageUtil.getAuthenticationErrorPasswordString()));	
		}
		
	}
	
	public void validateUsername(FacesContext facesContext, UIComponent component, Object value)throws ValidatorException {
		String tmpUsername = (String) value;
		User tmpUser = userService.findUserByName(tmpUsername);
		
		if(tmpUser == null) {
			throw new ValidatorException(new FacesMessage(I18nMessageUtil.getAuthenticationErrorUsernameString()));		
		}
	}
	
	public void postValidateUsername(ComponentSystemEvent event) {
		HtmlInputText inputText = (HtmlInputText)event.getComponent();
		if (inputText.getValue() != null) {
			this.username = inputText.getValue().toString();
		}
	}
	
	
}
