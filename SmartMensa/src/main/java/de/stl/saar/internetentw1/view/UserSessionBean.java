package de.stl.saar.internetentw1.view;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

//import de.stl.saar.internetent1.spacegame.model.interfaces.User;
//import de.stl.saar.internetent1.spacegame.service.classes.UserServiceImpl;
//import de.stl.saar.internetent1.spacegame.service.interfaces.UserService;
//
////@ManagedBean
////@SessionScoped
//public class UserSessionBean {
//	private User loggedInUser;
//	private UserService userService;
//	
//	@PostConstruct
//	public void initialize() {
//		userService = new UserServiceImpl();
//	}
//	
//	public boolean isUserLoggedIn() {
//		if (loggedInUser != null) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//	
//	public void logIn(final String username) {
//		loggedInUser = userService.findUserByName(username);
//	}
//	
//	public void logOut() {
//		loggedInUser = null;
//	}
//	
//	public void setLoggedInUser(User loggedInUser) {
//		this.loggedInUser = loggedInUser;
//	}
//	
//	public User getLoggedInUser() {
//		return loggedInUser;
//	}
//}
