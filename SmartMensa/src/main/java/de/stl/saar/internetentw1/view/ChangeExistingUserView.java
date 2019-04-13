package de.stl.saar.internetentw1.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;

import de.stl.saar.internetentw1.i18n.I18nMessageUtil;
import de.stl.saar.internetentw1.model.Role;
import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.service.classes.RoleServiceImpl;
import de.stl.saar.internetentw1.service.interfaces.RoleService;
import de.stl.saar.internetentw1.service.interfaces.UserService;
import de.stl.saar.internetentw1.utils.JsfUtils;
import de.stl.saar.internetentw1.utils.RandomUtils;

/**
 * Diese Klasse repraesentiert das Fenster zum Veraendern eines bestehenden Users
 * in der Datenbank. Das Fenster kann nur von Admins aufgerufen werden.
 * 
 * Das Email-Feld wird nicht verwendet
 * 
 * Zugehoerige xhtml-Datei: "changeExistingUser.xhtml"
 * 
 * @author Michelle Blau
 *
 */

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
	private UserService userService;
	private RoleService roleService;

	/**
	 * Nimmt den zu Aendernden User anhand des Usernamens aus der Datenbank.
	 * Der Username wurde zuvor als Parameter mitgegeben.
	 */
	@PostConstruct
	public void initialize() {
		currentUser = JsfUtils.getCurrentUserBeanAttribute();
		userService = JsfUtils.getUserServiceBeanAttribute();
		roleService = new RoleServiceImpl();

		String selectedUsernameParam = JsfUtils.getParameterByName("selectedUserName");
		userToChange = userService.findUserByName(selectedUsernameParam);
		
		isPasswordChangeNecessary = userToChange.getIsPasswordChangeNecessary();
		password = userToChange.getPassword();
		username = userToChange.getUsername();
		role = userToChange.getRole().toString();
		userID = userToChange.getUserId();
		
		roleList = roleService.findAllRoles();
		
		userList = userService.findAllUsers();
	}
	
	/**
	 * Veraendert die Werte des ausgewaehlten Users
	 * 
	 * @param event - nicht verwendet
	 */
	public void saveChangesToSelectedUser(ActionEvent event) {
		userToChange.setUsername(username);
		userToChange.setPassword(password);
		userToChange.setRole(roleService.findRoleByName(role));
		userToChange.setIsPasswordChangeNecessary(isPasswordChangeNecessary);
			
	}
	
	/**
	 * Erzeugt ein zufaelliges Passwort. Falls dies durch einen Admin
	 * geschieht, wird der betroffene User beim naechsten Login zur
	 * Aenderung seines Passworts aufgefordert.
	 * 
	 * @param event - nicht verwendet
	 */
	public void createRandomPassword(ActionEvent event) {
		if(currentUser.isAdmin()) {
			isPasswordChangeNecessary = true;
		}
		password = RandomUtils.createRandomString();
	}

	/**
	 * Prueft, ob ein angegebener Username bereits existiert.
	 * Ist dies der Fall, wird eine ValidatorException geworfen.
	 * 
	 * @param facesContext - hier nicht verwendet
	 * @param component - hier nicht verwendet
	 * @param value - der zu pruefende Username
	 * @throws ValidatorException
	 */
	public void validateUsername(FacesContext facesContext, UIComponent component, Object value)throws ValidatorException {
		String tmpUsername = (String) value;
		
		if(userService.doesUsernameExist(tmpUsername)) {
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
