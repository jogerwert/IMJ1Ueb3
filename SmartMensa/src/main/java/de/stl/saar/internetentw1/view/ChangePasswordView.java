package de.stl.saar.internetentw1.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
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
import de.stl.saar.internetentw1.service.interfaces.UserService;
import de.stl.saar.internetentw1.utils.JsfUtils;
import de.stl.saar.internetentw1.utils.StringUtils;
import de.stl.saar.internetentw1.constants.*;


/**
 * Diese Klasse repraesentiert das Fenster, in welchem der Benutzer sich ins Spiel einloggen kann.
 * @author Christopher
 *
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
