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
 * Diese Klasse repraesentiert das Fenster, in dem ein User seine Daten veraendern kann.
 * Admins koennen auch neue User anlegen.
 * 
 * Das Email-Feld wird nicht verwendet
 * 
 * 
 * Zugehoerige xhtml-Datei: "editProfile.xhtml"
 * 
 * @author Michelle Blau
 */

@ManagedBean
@ViewScoped
public class EditProfileView {
	private User currentUser;
	private List<Role> roleList;
	private String password;
	private String username;
	private String isAdmin;
	private String isNotAdmin; //wird nicht verwendet
	private String role;
	private List<User> userList;
	private int userID;
	private boolean isPasswordChangeNecessary;
	private UserService userService;
	private RoleService roleService;

	
	/**
	 * Stellt die Daten des aktuell eingeloggten Users dar.
	 * Die Ansicht des Fensters ist unterschiedlich, je nach dem, ob der
	 * eingeloggte User ein Admin ist oder nicht
	 */
	@PostConstruct
	public void initialize() {
		currentUser = JsfUtils.getCurrentUserBeanAttribute();
		userService = JsfUtils.getUserServiceBeanAttribute();
		roleService = new RoleServiceImpl();

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
		
		roleList = roleService.findAllRoles();
		
		userList = userService.findAllUsers();
	}
	
	/**
	 * Speichert die angegebenen Aenderungen des aktuell
	 * eingeloggten Users in der Datenbank.
	 * 
	 * @param event - nicht verwendet
	 */
	public void saveChangesToCurrentUser(ActionEvent event) {
		if(currentUser.isAdmin()) {
			currentUser.setUsername(username);
			currentUser.setPassword(password);
			currentUser.setRole(roleService.findRoleByName(role));
			currentUser.setIsPasswordChangeNecessary(isPasswordChangeNecessary);
		}else {
			currentUser.setUsername(username);
			currentUser.setPassword(password);
		}
			
	}
	
	/**
	 * Erstellt einen neuen User in der Datenbank mit den angegebenen Daten.
	 * Nur fuer Admins moeglich.
	 * @param event - nicht verwendet
	 */
	public void createNewUser(ActionEvent event) {
		if(currentUser.isAdmin()) {
			Role selectedRole = roleService.findRoleByName(role);
			User newUser = new User(0, username, password, selectedRole );
			newUser.setIsPasswordChangeNecessary(isPasswordChangeNecessary);
			userService.addUser(newUser);
		}	
	}
	
	/**
	 * Erzeugt ein zufaelliges Passwort. Wird dies durch den Admin
	 * getan, wird die entsprechende CheckBox gesetzt.
	 * @param event - nicht verwendet
	 */
	public void createRandomPassword(ActionEvent event) {
		if(currentUser.isAdmin()) {
			isPasswordChangeNecessary = true;
		}
		password = RandomUtils.createRandomString();
	}
	
	/**
	 * Prueft, ob ein angegebener Username bereits in der Datenbank existiert.
	 * Ist dies der Fall, wird eine ValidatorException geworfen
	 * 
	 * @param facesContext - nicht verwendet
	 * @param component - nicht verwendet
	 * @param value - der zu pruefende Username
	 * @throws ValidatorException 
	 */
	public void validateUsername(FacesContext facesContext, UIComponent component, Object value)throws ValidatorException {
		String tmpUsername = (String) value;
		
		if(userService.doesUsernameExist(tmpUsername)) {
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
