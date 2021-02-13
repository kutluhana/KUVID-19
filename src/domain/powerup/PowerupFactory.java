package domain.powerup;

import java.util.Random;


/**
 * @author MehmetUstek
 *
 */
public class PowerupFactory {
	/**
	 * OVERVIEW: This class is a factory class that takes an powerup and modifies it.
	 */
	static Random random = new Random();
	private static Powerup powerup;
	public static Powerup getPU(Powerup pu,String type) {
		/**
		 * @requires a valid Powerup object and a string which may be either empty string or the string with a valid powerup type.
		 * @modifies the given powerup's type.
		 * @effects given a powerup, return that powerup with target type.
		 */
		pu.setType(type);
		return pu;
	}
	public static Powerup getPU() {
		/**
		 * @modifies the given Powerup's type.
		 * @effects given an powerup, randomly choose the target type and change the atom's type accordingly.
		 */
		int i = random.nextInt(4);
		switch(i) {
		case 0:
			powerup= new Powerup("+alpha");
			break;
		case 1:
			powerup= new Powerup("+beta");
			break;
		case 2:
			powerup= new Powerup("+sigma");
			break;
		case 3:
			powerup= new Powerup("+gamma");
			break;
		}
		return powerup;
	}
	public static Powerup getPU(String type) {
		powerup = new Powerup(type);
		return powerup;
	}
	
}
