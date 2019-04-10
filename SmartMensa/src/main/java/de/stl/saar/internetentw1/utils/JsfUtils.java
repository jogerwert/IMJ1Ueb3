package de.stl.saar.internetentw1.utils;

import java.util.Locale;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.service.classes.UserServiceImpl;
import de.stl.saar.internetentw1.service.interfaces.DishService;
import de.stl.saar.internetentw1.service.interfaces.UserService;
import de.stl.saar.internetentw1.service.classes.DishServiceImpl;

public class JsfUtils {
	private static final String PARAM_USER_ID = "userId";
	private static final String LOGINVIEW_SESSION_BEAN = "loginView";
	
	public static long getUserIdParameter() {
		final String userIdAsString = getParameterByName(PARAM_USER_ID);
		if (StringUtils.isNotEmpty(userIdAsString)) {
			final long userId = Long.parseLong(userIdAsString);
			return userId;
		} else {
			return 0;
		}
	}

	/**
	 * Ermittelt aus einer ManagedBean ein Attribut, das ein Dto-Objekt ist.
	 * @param attributeName Der Name des Attributs.
	 * @param beanName Der Name der ManagedBean.
	 * @return Das Dto-Objekt.
	 */
	public static Object getBeanAttribute(String attributeName, String beanName, Class objectClass) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Application application = facesContext.getApplication();
		ExpressionFactory expressionFactory = application.getExpressionFactory();
		ELContext elContext = facesContext.getELContext();
		String elExpression = "#{" + beanName + "." + attributeName + "}";
		return expressionFactory.createValueExpression(elContext, elExpression, objectClass).getValue(elContext);
	}
	
	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public static String getParameterByName(final String parameterName) {
		final ExternalContext externalContext = getExternalContext();
		return externalContext.getRequestParameterMap().get(parameterName);
	}

	public static ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	public static void addErrorMessageToContext(String title, final String text) {
		final FacesContext facesContext = getFacesContext();

		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, text));
	}

	public static void addInfoMessageToContext(String title, final String text) {
		final FacesContext facesContext = getFacesContext();

		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, text));
	}
	
	public static void setValueExpression(final String beanName, final String propertyName, final Class<?> theClass,
			final String value) {
		final String expression = "#{" + beanName + "." + propertyName + "}";
		final ExpressionFactory e = getExpressionFactory(getFacesContext());
		final ValueExpression valueExpression = e.createValueExpression(getELContext(), expression, theClass);
		valueExpression.setValue(getELContext(), value);
	}

	public static ExpressionFactory getExpressionFactory(final FacesContext facesContext) {
		return getApplication(facesContext).getExpressionFactory();
	}

	public static Application getApplication(final FacesContext facesContext) {
		final Application application = facesContext.getApplication();
		return application;
	}

	public static ELContext getELContext() {
		return FacesContext.getCurrentInstance().getELContext();
	}

	public static void setLocale(Locale locale) {
		getFacesContext().getViewRoot().setLocale(locale);
	}
	
	/**
	 * Gibt den Aktuell eingeloggten User aus der Session-Scoped ManagedBean "LoginView" zurueck
	 * @return aktuell eingeloggter User
	 */
	public static User getCurrentUserBeanAttribute() {
		User currentUser = (User)JsfUtils.getBeanAttribute("currentUser", LOGINVIEW_SESSION_BEAN, User.class);
		return currentUser;
	}
	
	/**
	 * Gibt das aktuell verwendete UserService-Objekt aus der Session-Scoped ManagedBean "LoginView" zurueck.
	 * Dieses Objekt sorgt dafür, dass Änderungen in der User-Datenbank auch über die gesamte Session hinweg sichtbar sind.
	 * @return aktuell verwendeter UserService
	 */
	public static UserService getUserServiceBeanAttribute() {
		UserService userService = (UserServiceImpl)JsfUtils.getBeanAttribute("userService", LOGINVIEW_SESSION_BEAN, UserServiceImpl.class);
		return userService;
	}
	
	/**
	 * Gibt das aktuell verwendete DishService-Objekt aus der Session-Scoped ManagedBean "LoginView" zurueck.
	 * Dieses Objekt sorgt dafür, dass Änderungen in der Dish-Datenbank auch über die gesamte Session hinweg sichtbar sind.
	 * @return aktuell verwendeter DishService
	 */
	public static DishService getDishServiceBeanAttribute() {
		DishService dishService = (DishServiceImpl)JsfUtils.getBeanAttribute("dishService", LOGINVIEW_SESSION_BEAN, DishServiceImpl.class);
		return dishService;
	}
}

// TO_SLIDES
// http://incepttechnologies.blogspot.com/p/view-parameters-in-jsf-20.html
