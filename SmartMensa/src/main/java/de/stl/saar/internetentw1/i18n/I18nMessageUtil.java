package de.stl.saar.internetentw1.i18n;

import java.util.ResourceBundle;

/**
 * Encapsulates the keys for internationalization. Java code is not allowed
 * to call the message bundles but it has to use this class and its static
 * methods. Therefore the other classes do not have to know the names of 
 * the keys. This class contains the keys only for messages that are not error
 * messages.
 * @author Christopher Olbertz
 *
 */
public class I18nMessageUtil {
	private static final String INVALID_EMAIL = "invalidEmailaddress";
	private static final String NOT_A_HTW_EMAIL = "notAHtwEmail";
	private static final String PASSWORDS_NOT_EQUAL= "passwordsNotEqual";
	private static final String AUTHENTICATION_ERROR= "authenticationError";
	private static final String AUTHENTICATION_USERNAME_ERROR= "authenticationErrorUsername";
	
	private static final String USERNAME_ALREADY_EXISTS_ERROR= "userNameAlreadyExistsError";
	
	private static final String LINK_MANAGE_DISHES= "manageDishes";
	private static final String LINK_MANAGE_USERS= "manageUsers";
	
	
	
	
	private static ResourceBundle messagesResourceBundle;
	private static ResourceBundle labelsResourceBundle;
	
	static {
		messagesResourceBundle = I18nUtil.getMessagesResourceBundle();
		labelsResourceBundle = I18nUtil.getComponentLabelsResourceBundle();
	}

//-------------------------Messages---------------------------------
	public static final String getInvalidEmailString() {
		return messagesResourceBundle.getString(INVALID_EMAIL);
	}
	
	public static final String getNotAHtwEmailString() {
		return messagesResourceBundle.getString(NOT_A_HTW_EMAIL);
	}
	
	public static final String getPasswordsNotEqualString() {
		return messagesResourceBundle.getString(PASSWORDS_NOT_EQUAL);
	}
	
	public static final String getAuthenticationErrorString() {
		return messagesResourceBundle.getString(AUTHENTICATION_ERROR);
	}
	
	public static final String getAuthenticationErrorUsernameString() {
		return messagesResourceBundle.getString(AUTHENTICATION_USERNAME_ERROR);
	}
	
	public static String getErrorUsernameAlreadyExistsString() {
		return messagesResourceBundle.getString(USERNAME_ALREADY_EXISTS_ERROR);
	}

	
	//-------------------------Labels---------------------------------	  
	public static final String getLinkManageDishesString() {
		return labelsResourceBundle.getString(LINK_MANAGE_DISHES);
	}
	
	public static final String getLinkManageUsersString() {
		return labelsResourceBundle.getString(LINK_MANAGE_USERS);
	}



}
