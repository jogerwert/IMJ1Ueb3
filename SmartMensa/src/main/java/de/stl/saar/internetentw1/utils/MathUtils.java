package de.stl.saar.internetentw1.utils;

/**
 * Stellt Werkzeugmethoden zur Verarbeitung von Zahlen-Datentypen
 * zur Verfuegung
 * 
 * @author Michelle Blau
 *
 */
public class MathUtils {

	/**
	 * Prueft ob eine uebergebene Zahl in einem angegebenen Intervall liegt.
	 * 
	 * @param value - zu pruefende Zahl
	 * @param min - linke Intervallgrenze
	 * @param max - rechte Intervallgrenze
	 * 
	 * @return true, wenn value im Intervall liegt, sonst false
	 */
	public static boolean isValueInRange(int value, int min, int max) {
		return ( (value <= max) && (value >= min) );
	}
	
}
