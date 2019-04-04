package de.stl.saar.internetentw1.view;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.utils.JsfUtils;

/**
 * Diese Klasse verwaltet die Hauptnavigation für normale User und Admins
 * @author Michelle Blau
 *
 */

@ManagedBean
@RequestScoped
public class LinkView {
	private String isAdmin;
	private User currentUser;	
	
	
	@PostConstruct
	public void initialize() {
		currentUser = (User)JsfUtils.getBeanAttribute("currentUser", "loginView", User.class);
		if(currentUser.isAdmin()) {
			isAdmin = "true";
		}else {
			isAdmin = "false";
		}
		
		
	}

	public String logout() {
		currentUser = null;
		
		return "login";
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
}
