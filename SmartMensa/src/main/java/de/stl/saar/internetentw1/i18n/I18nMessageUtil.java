package de.stl.saar.internetentw1.i18n;

import java.util.ResourceBundle;

/**
 * Encapsulates the keys for internationalization. Java code is not allowed
 * to call the message bundles but it has to use this class and its static
 * methods. Therefore the other classes do not have to know the names of 
 * the keys. 
 * @author Christopher Olbertz
 * @author Johannes Gerwert
 * @author Michelle Blau
 *
 */
public class I18nMessageUtil {
	private static final String INVALID_EMAIL = "invalidEmailaddress";
	private static final String NOT_A_HTW_EMAIL = "notAHtwEmail";
	private static final String PASSWORDS_NOT_EQUAL= "passwordsNotEqual";
	private static final String AUTHENTICATION_ERROR= "authenticationError";
	private static final String AUTHENTICATION_USERNAME_ERROR= "authenticationErrorUsername";
	
	private static final String USERNAME_ALREADY_EXISTS_ERROR= "userNameAlreadyExistsError";
	
	private static final String DISH_CHANGE_ERROR_NAME_EMPTY= "dishChangeErrorNameEmpty";
	private static final String DISH_CHANGE_ERROR_PRICE_EMPTY= "dishChangeErrorPriceEmpty";
	private static final String DISH_CHANGE_ERROR_PRICE_NOT_A_NUMBER= "dishChangeErrorPriceNotANumber";
	private static final String DISH_CHANGE_ERROR_PRICE_WRONG= "dishChangeErrorPriceWrong";
	private static final String DISH_CHANGE_ERROR_CATEGORY_EMPTY= "dishChangeErrorCategoryEmpty";
	
	private static final String DISH_ADDED_SUMMARY= "dishAddedSummary";
	private static final String DISH_ADDED_DETAIL= "dishAddedDetail";
	private static final String DISH_CHANGED_SUMMARY= "dishChangedSummary";
	private static final String DISH_CHANGED_DETAIL= "dishChangedDetail";
	
	private static final String LINK_MANAGE_DISHES= "manageDishes";
	private static final String LINK_MANAGE_USERS= "manageUsers";
	
	private static final String ROOM_ERROR = "roomError";
	private static final String BUILDING_NUMBER_ERROR ="buildingNumberError";
	private static final String FLOOR_NUMBER_ERROR = "floorNumberError";
	private static final String ROOM_NUMBER_ERROR = "roomNumberError";
	
	
	
	
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
	
	public static String getDishChangeErrorNameEmpty() {
		return messagesResourceBundle.getString(DISH_CHANGE_ERROR_NAME_EMPTY);
	}
	
	public static String getDishChangeErrorPriceEmpty() {
		return messagesResourceBundle.getString(DISH_CHANGE_ERROR_PRICE_EMPTY);
	}
	
	public static String getDishChangeErrorPriceNotANumber() {
		return messagesResourceBundle.getString(DISH_CHANGE_ERROR_PRICE_NOT_A_NUMBER);
	}
	
	public static String getDishChangeErrorPriceWrong() {
		return messagesResourceBundle.getString(DISH_CHANGE_ERROR_PRICE_WRONG);
	}
	
	public static String getDishChangeErrorCategoryEmpty() {
		return messagesResourceBundle.getString(DISH_CHANGE_ERROR_CATEGORY_EMPTY);
	}
	
	public static final String getDishAddedSummary() {
		return messagesResourceBundle.getString(DISH_ADDED_SUMMARY);
	}
	
	public static final String getDishAddedDetail() {
		return messagesResourceBundle.getString(DISH_ADDED_DETAIL);
	}
	
	public static final String getDishChangedSummary() {
		return messagesResourceBundle.getString(DISH_CHANGED_SUMMARY);
	}
	
	public static final String getDishChangedDetail() {
		return messagesResourceBundle.getString(DISH_CHANGED_DETAIL);
	}
	
	public static String getRoomErrorString() {
		return messagesResourceBundle.getString(ROOM_ERROR);
	}

	public static String getBuildingNumberErrorString() {
		return messagesResourceBundle.getString(BUILDING_NUMBER_ERROR);
	}

	public static String getFloorNumberErrorString() {
		return messagesResourceBundle.getString(FLOOR_NUMBER_ERROR);
	}
	
	public static String getRoomNumberErrorString() {
		return messagesResourceBundle.getString(ROOM_NUMBER_ERROR);
	}



	//-------------------------Labels---------------------------------	  
	public static final String getLinkManageDishesString() {
		return labelsResourceBundle.getString(LINK_MANAGE_DISHES);
	}
	
	public static final String getLinkManageUsersString() {
		return labelsResourceBundle.getString(LINK_MANAGE_USERS);
	}




}
