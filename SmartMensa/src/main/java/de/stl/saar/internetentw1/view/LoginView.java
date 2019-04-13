package de.stl.saar.internetentw1.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.validator.ValidatorException;
import de.stl.saar.internetentw1.i18n.I18nMessageUtil;
import de.stl.saar.internetentw1.model.*;
import de.stl.saar.internetentw1.service.classes.DishServiceImpl;
import de.stl.saar.internetentw1.service.classes.UserServiceImpl;
import de.stl.saar.internetentw1.service.interfaces.DishService;
import de.stl.saar.internetentw1.service.interfaces.UserService;


/**
 * Diese Klasse repraesentiert das Fenster, in welchem der Benutzer sich einloggen kann.
 * Ihre Attribute userService, dishService und currentUser werden von den anderen
 * ManagedBeans verwendet.
 * 
 * @author Christopher
 * @author Michelle Blau
 */

@ManagedBean
@SessionScoped
public class LoginView {
	private UserService userService;
	private DishService dishService;
	private String username;
	private String password;
	private User currentUser;	
	private String selectedUserName;
	
	@PostConstruct
	public void initializeBean() {
		userService = new UserServiceImpl();
		dishService = new DishServiceImpl();
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

	/**
	 * Loggt den angegebenen User ein. Danach wird der User entweder
	 * zum Fenster fuer die Passwortaenderung weitergeleitet oder 
	 * zur Hauptnavigationsseite.
	 * 
	 * @return Dateiname fuer die Seite zur Passwortaenderung/Hauptnavigation oder null falls User nicht existiert
	 */
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

	public String logout() {
		currentUser = null;
		
		return "login";
	}
	

/**
 * Ueberprueft das eingegebene Passwort. Existiert kein User mit dem angegebenen Passwort in der Datenbank, so wird
 * eine ValidatorException geworfen.
 * 
 * @param facesContext - nicht verwendet
 * @param component - nicht verwendet
 * @param value - das eingegebene Passwort
 * @throws ValidatorException
 */
	public void validatePassword(FacesContext facesContext, UIComponent component, Object value)throws ValidatorException {
		String tmpPassword = (String) value;
		User tmpUser = null;

		List<User> userList = this.userService.findAllUsers();
		for(User u : userList) {
			if(u.getUsername().equals(this.username)) {
				if(u.getPassword().equals(tmpPassword)) {
					tmpUser = u;
				}
			}
		}
		
		if(tmpUser == null) {
			throw new ValidatorException(new FacesMessage(I18nMessageUtil.getAuthenticationErrorString()));	
		}
		
	}
	
	/**
	 * Ueberprueft, ob ein angegebener Username existiert. Ist dies nicht der Fall, wird eine ValidatorException
	 * geworfen.
	 * 
	 * @param facesContext - nicht verwendet
	 * @param component - nicht verwendet
	 * @param value - der Username
	 * @throws ValidatorException
	 */
	public void validateUsername(FacesContext facesContext, UIComponent component, Object value)throws ValidatorException {
		String tmpUsername = (String) value;
		
		if(!userService.doesUsernameExist(tmpUsername)) {
			throw new ValidatorException(new FacesMessage(I18nMessageUtil.getAuthenticationErrorUsernameString()));		
		}
	}
	
	/**
	 * Uebernimmt den Usernamen, bevor das Passwort validiert wird, damit "validatePassword()"
	 * ueberpruefen kann, ob sowohl Passwort als auch Username in der Datenbank vorhanden sind.
	 * 
	 * Dies ist notwendig, da man normalerweise nur einzelne Eingabefelder validieren kann.
	 * 
	 * @param event
	 */
	public void postValidateUsername(ComponentSystemEvent event) {
		HtmlInputText inputText = (HtmlInputText)event.getComponent();
		if (inputText.getValue() != null) {
			this.username = inputText.getValue().toString();
		}
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

	public String getSelectedUserName() {
		return selectedUserName;
	}

	public void setSelectedUserName(String selectedUserName) {
		this.selectedUserName = selectedUserName;
	}
	
}
