package de.stl.saar.internetentw1.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import de.stl.saar.internetentw1.model.Dish;
import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.service.classes.DishServiceImpl;
import de.stl.saar.internetentw1.service.interfaces.DishService;
import de.stl.saar.internetentw1.utils.JsfUtils;


/**
 * Controller der jsf-xhtmls manageDishes und dishesTable.
 * Diese Klasse stellt den genannten xhtmls benoetigte Variablen und Methoden zur 
 * Verfuegung.
 * 
 * @author Johannes Gerwert
 * @version 12.04.19
 */
@ManagedBean
@ViewScoped
public class ManageDishesView {
	
	private DishService dishService;
	private User currentUser;
	private DataModel<Dish> dishesList;
	
	/**
	 * Initialisierung der Attribute.
	 * Diese Methode wird von JSF nach Erstellen des Objektes automatisch aufgerufen.
	 */
	@PostConstruct
	public void initialize() {
		dishService = new DishServiceImpl();
		currentUser = (User) JsfUtils.getBeanAttribute("currentUser", "loginView", User.class);
		List<Dish> dishes = dishService.findAllDishes();
		dishesList = new ListDataModel<Dish>();
		dishesList.setWrappedData(dishes);
	}
	
	/**
	 * Wenn die entsprechende Schaltflaeche betaetigt wird, wird diese Methode aufgerufen.
	 * Der Befehl zum Loeschen des ausgewaehlten Gerichtes wird an die Service-Schicht gesendet.
	 * 
	 * @param event Das Event, durch das diese Methode aufgerufen wurde. Kontextdaten werden
	 * 				bereitgestellt.
	 */
	public void removeDish(ActionEvent event) {
		Dish selectedDish = dishesList.getRowData();
		dishService.removeDishById(selectedDish.getDishId());
	}
	
	public DataModel<Dish> getDishesList() {
		return dishesList;
	}

	public void setDishesList(DataModel<Dish> dishesList) {
		this.dishesList = dishesList;
	}

	public User getCurrentUser() {
		return currentUser;
	}

}
