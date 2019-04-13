package de.stl.saar.internetentw1.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import de.stl.saar.internetentw1.i18n.I18nMessageUtil;
import de.stl.saar.internetentw1.model.Room;

/**
 * Anwendungsdefinierter Konvertierer fuer die Modellklasse "Room".
 * (Um Exceptions zu vermeiden sollte die Modellklasse "Room" das Interface
 * Serializable implementieren)
 * 
 * @author Michelle Blau
 *
 */

@FacesConverter(forClass = Room.class)
public class RoomConverter implements Converter {
	
	private static final String FOUR_DIGITS_REGEX = "\\d{4}";
	private static final String THREE_DIGITS_REGEX = "\\d{3}";

	/**
	 * Konvertiert einen gegebenen String zu einem Room-Objekt.
	 * Erstellt eine FacesMessage, falls der uebergebene String keine 3 bis 4 -stellige
	 * Zahl darstellt.
	 * 
	 * @param context - wird zur Erstellung der FacesMessage verwendet
	 * @param component - wird zur Erstellung der FacesMessage verwendet
	 * @param value - ist der String, der konvertiert wird
	 * 
	 * @return Room-Objekt oder null, falls keine 3 bis 4 -stellige Zahl
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value.matches(FOUR_DIGITS_REGEX)) {
			int buildingNumber = Character.getNumericValue((value.charAt(0)));
			int floorNumber = Character.getNumericValue((value.charAt(1)));
			int roomNumber = Integer.parseInt(value.substring(2));
			
			Room room = new Room(buildingNumber, floorNumber, roomNumber);
			return room;
			
		}else if(value.matches(THREE_DIGITS_REGEX)) {
			int buildingNumber = Character.getNumericValue((value.charAt(0)));
			int floorNumber = Character.getNumericValue((value.charAt(1)));
			int roomNumber = Character.getNumericValue((value.charAt(2)));;
			
			Room room = new Room(buildingNumber, floorNumber, roomNumber);
			return room;
		}else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, I18nMessageUtil.getRoomErrorString(), I18nMessageUtil.getRoomErrorString());
			context.addMessage(component.getClientId(), message);
			return null;
		}

	}

	/**
	 * Konvertiert ein Room-Objekt in einen String. Dabei werden fuehrende Nullen fuer
	 * die Raumnummer beruecksichtigt. 
	 * Bsp.: Room(8,2,3) wird zu "8203"
	 * 
	 * @param context - wird hier nicht verwendet
	 * @param component - wird hier nicht verwendet
	 * @param value - ist das zu konvertierende Room-Objekt
	 * 
	 * @return String-Darstellung des Room-Objekts
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Room room = (Room) value;
		if(room.getRoom()<10) {
			String resultWithCorrectRoomNumber ="";
			resultWithCorrectRoomNumber += room.getBuilding();
			resultWithCorrectRoomNumber += room.getFloor() + "0";
			resultWithCorrectRoomNumber += room.getRoom();
			
			return resultWithCorrectRoomNumber;
		}
		
		return value.toString();
	}

}
