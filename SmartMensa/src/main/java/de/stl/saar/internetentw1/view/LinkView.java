package de.stl.saar.internetentw1.view;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.utils.JsfUtils;

/**
 * Diese Klasse repraesentiert das Hauptnavigations-Fenster für normale User und Admins.
 * Die zugehoerige xhtml-Datei ist "overview.xhtml"
 * 
 * @author Michelle Blau
 *
 */

@ManagedBean
@ViewScoped
public class LinkView {
	private String isAdmin;
	private User currentUser;	
	private String selectedUserName; //nicht verwendet

	
	
	@PostConstruct
	public void initialize() {
		currentUser = JsfUtils.getCurrentUserBeanAttribute();
		
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

	public String getSelectedUserName() {
		return selectedUserName;
	}

	public void setSelectedUserName(String selectedUserName) {
		this.selectedUserName = selectedUserName;
	}



}
