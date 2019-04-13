package de.stl.saar.internetentw1.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import de.stl.saar.internetentw1.i18n.I18nMessageUtil;
import de.stl.saar.internetentw1.utils.MathUtils;

/**
 * Anwendungsdefinierter Validierer fuer die Modellklasse "Room".
 * 
 * @author Michelle Blau
 *
 */

@FacesValidator("roomValidator")
public class RoomValidator  implements Validator {
	
	private static final int BUILDING_MAX_NUMBER = 8;
	private static final int BUILDING_MIN_NUMBER = 1;
	
	private static final int FLOOR_MAX_NUMBER = 2;
	private static final int FLOOR_MIN_NUMBER = 0;
	
	private static final int ROOM_MAX_NUMBER = 20;
	private static final int ROOM_MIN_NUMBER = 1;

	
	/**
	 * Ueberprueft zu einem uebergebenen Raum-Objekt, ob 
	 * -Die Gebaeudenummer im Intervall  [1;8]
	 * -Die Etagennummer im Intervall  [0;2]
	 * -Die Raumnummer im Intervall  [1;20] liegt.
	 * 
	 * Ist eine der Bedingungen nicht erfuellt, wird eine entsprechende ValidatorException geworfen
	 * 
	 * @param context - wird nicht verwendet
	 * @param component - wird nicht verwendet
	 * @param value - das Raum-Objekt
	 */
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String room = value.toString();
		int buildingNumber = 0;
		int floorNumber = 0;
		int roomNumber = 0;

		if(room.length() == 4) {
			buildingNumber = Character.getNumericValue((room.charAt(0)));
			floorNumber = Character.getNumericValue((room.charAt(1)));
			roomNumber = Integer.parseInt(room.substring(2));
		}else if (room.length() == 3){
			buildingNumber = Character.getNumericValue((room.charAt(0)));
			floorNumber = Character.getNumericValue((room.charAt(1)));
			roomNumber = Character.getNumericValue((room.charAt(2)));
		}
		
		if( ! MathUtils.isValueInRange(buildingNumber, BUILDING_MIN_NUMBER, BUILDING_MAX_NUMBER) ) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, I18nMessageUtil.getBuildingNumberErrorString(), I18nMessageUtil.getBuildingNumberErrorString()));
		}
		
		if( ! MathUtils.isValueInRange(floorNumber, FLOOR_MIN_NUMBER, FLOOR_MAX_NUMBER) ) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, I18nMessageUtil.getFloorNumberErrorString(), I18nMessageUtil.getFloorNumberErrorString()));
		}
		
		if( ! MathUtils.isValueInRange(roomNumber, ROOM_MIN_NUMBER, ROOM_MAX_NUMBER) ) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, I18nMessageUtil.getRoomNumberErrorString(), I18nMessageUtil.getRoomNumberErrorString()));
		}
	}

}