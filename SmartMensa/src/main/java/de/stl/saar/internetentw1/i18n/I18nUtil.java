package de.stl.saar.internetentw1.i18n;

import java.util.ResourceBundle;

/**
 * Encapsulates the resource bundles. Only the other classes in this package
 * are allowed to call the methods of this class. Therefore it is only
 * package public.
 * @author Christopher Olbertz
 *
 */
class I18nUtil {
	private static final String I18N_BASENAME_COMPONENT_LABELS = "i18n/labels";
	private static final String I18N_BASENAME_MESSAGES = "i18n/messages";
	
	private static ResourceBundle resourceBundleComponentLabels;
	private static ResourceBundle resourceBundleExceptions;
	private static ResourceBundle resourceBundleMessages;
	
	static {
		resourceBundleComponentLabels = ResourceBundle.getBundle(I18N_BASENAME_COMPONENT_LABELS);
		resourceBundleMessages = ResourceBundle.getBundle(I18N_BASENAME_MESSAGES);
	}
	
	public static ResourceBundle getComponentLabelsResourceBundle() {
		return resourceBundleComponentLabels;
	}
	
	public static ResourceBundle getExceptionsResourceBundle() {
		return resourceBundleExceptions;
	}
	
	public static ResourceBundle getMessagesResourceBundle() {
		return resourceBundleMessages;
	}
}