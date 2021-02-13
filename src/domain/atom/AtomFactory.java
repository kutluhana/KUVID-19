package domain.atom;

import java.util.Random;


/**
 * @author MehmetUstek
 *
 */
public class AtomFactory {
	/**
	 * OVERVIEW: This class is a factory class that takes an atom and modifies it.
	 */
	public static Random random = new Random();
	public static Atom getAtom(Atom atom,String type) {
		/**
		 * @requires a valid Atom object and a string which may be either empty string or the string with a valid atom type.
		 * @modifies the given Atom's type.
		 * @effects given an atom, randomly choose the target type and change the atom's type accordingly. If atom's target type
		 * specified explicitly, then make the atom to  be target atom type.
		 */
		if(type== "") {
			int i = random.nextInt(4);
			switch(i) {
			case 0:
				atom.setType("alpha");
				break;
			case 1:
				atom.setType("beta");
				break;
			case 2:
				atom.setType("sigma");
				break;
			case 3:
				atom.setType("gamma");
				break;
			}
			return atom;
		}
		else {
			atom.setType(type);
			return atom;
		}
		
	}
}
