package de.stl.saar.internetentw1.view;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import de.stl.saar.internetentw1.model.*;
import de.stl.saar.internetentw1.service.interfaces.UserService;
import de.stl.saar.internetentw1.utils.JsfUtils;


/**
 * Diese Klasse repraesentiert das Fenster, das den Nutzer zur
 * Aenderung seines Passwortes auffordert.
 * 
 * Zugehoerige xhtml-Datei: "changePassword.xhtml"
 * 
 * @author Michelle Blau
 */

@ManagedBean
@ViewScoped
public class ChangePasswordView {
	private UserService userService;
	private String password;
	private User currentUser;	
	
	@PostConstruct
	public void initializeBean() {
		currentUser = JsfUtils.getCurrentUserBeanAttribute();
		userService = JsfUtils.getUserServiceBeanAttribute();
		password = currentUser.getPassword();
	}

	/**
	 * Uebernimmt das neu angegebene Passwort in der Datenbank
	 * 
	 * @return String, der den Dateinamen der Hauptnavigationsseite enthaelt
	 */
	public String saveAndGoToOverview() {
		currentUser.setPassword(password);
		currentUser.setIsPasswordChangeNecessary(false);
		
		return "overview";
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
	

	
}
