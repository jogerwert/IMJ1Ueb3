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
	private static final String AUTHENTICATION_PASSWORD_ERROR= "authenticationErrorPassword";
	private static final String AUTHENTICATION_USERNAME_ERROR="authenticationErrorUsername";
	
	
	private static ResourceBundle messagesResourceBundle;
	
	static {
		messagesResourceBundle = I18nUtil.getMessagesResourceBundle();
	}

	public static final String getInvalidEmailString() {
		return messagesResourceBundle.getString(INVALID_EMAIL);
	}
	
	public static final String getNotAHtwEmailString() {
		return messagesResourceBundle.getString(NOT_A_HTW_EMAIL);
	}
	
	public static final String getPasswordsNotEqualString() {
		return messagesResourceBundle.getString(PASSWORDS_NOT_EQUAL);
	}
	
	public static final String getAuthenticationErrorPasswordString() {
		return messagesResourceBundle.getString(AUTHENTICATION_PASSWORD_ERROR);
	}
	
	public static final String getAuthenticationErrorUsernameString() {
		return messagesResourceBundle.getString(AUTHENTICATION_USERNAME_ERROR);
	}
}
