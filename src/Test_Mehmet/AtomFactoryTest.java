package Test_Mehmet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import domain.Controller;
import domain.atom.Atom;
import domain.atom.AtomFactory;

class AtomFactoryTest {
	/*
	 * Black-Box: Check returned atom's type's correctness.
	 */
	@Test
	void mustBeTrue() {
		Atom atom= new Atom("alpha");
		//AtomFactory with unspecified type. This has to return random type of atom.
		Atom testAtom= AtomFactory.getAtom(atom, "");
		assertTrue(Controller.isAtom(testAtom)); //Black-box: returns an atom with valid type.
		
		testAtom = AtomFactory.getAtom(atom, "alpha");
		assertTrue(testAtom.getType().equals("alpha")); //Black-box: given an alpha atom, returns an alpha atom.
		
		testAtom = AtomFactory.getAtom(atom, "beta");
		assertTrue(testAtom.getType().equals("beta")); //Black-box: given an beta atom,returns an beta atom.
		
		testAtom = AtomFactory.getAtom(atom, "sigma");
		assertTrue(testAtom.getType().equals("sigma")); //Black-box: given an sigma atom,returns an sigma atom.
		
		testAtom = AtomFactory.getAtom(atom, "gamma");
		assertTrue(testAtom.getType().equals("gamma")); //Black-box: given an gamma atom, returns an gamma atom.
	}
	
	@Test
	void mustBeFalse() {
		Atom atom= new Atom("alpha");
		//AtomFactory with unspecified type. This has to return random type of atom.
		Atom testAtom= AtomFactory.getAtom(atom, "");
		
		testAtom = AtomFactory.getAtom(atom, "beta");
		assertFalse(testAtom.getType().equals("alpha")); //Black-box: must return the specified atom not other types of atoms.
		
	}

}
