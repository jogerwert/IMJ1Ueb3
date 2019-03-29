package de.stl.saar.internetentw1.utils;

import java.util.Random;

/**
 * Enthaelt Zugriffsmethoden fuer die Erzeugung von Zufallszahlen. Kapselt
 * somit die gesamte Erzeugung von Zufallszahlen.
 * @author christopher
 *
 */
public class RandomUtils {
	private static final Random random;
	
	static {
		random = new Random();
	}
	
	/**
	 * Erzeugt eine double-Zufallszahl von 0 bis zu einem Maximalwert.
	 * @param max Der Maximalwert.
	 * @return Die erzeugte Zufallszahl.
	 */
	public static double nextDouble(double max) {
		return random.nextDouble() * max;
	}
	
	/**
	 * Erzeugt eine int-Zufallszahl von 0 bis zu einem Maximalwert.
	 * @param max Der Maximalwert.
	 * @return Die erzeugte Zufallszahl.
	 */
	public static int nextInt(int max) {
		return random.nextInt(max);
	}
	
	public static int nextInt() {
		return random.nextInt();
	}
}