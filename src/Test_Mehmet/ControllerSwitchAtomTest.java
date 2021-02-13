package Test_Mehmet;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import domain.Controller;
import domain.atom.Atom;
import domain.atom.AtomFactory;
import ui.Renderer;
import ui.UIAtom;

class ControllerSwitchAtomTest {
	/*
	 * Black-box: call the first element added before calling switch. Then call switch and test if true.
	 * Glass-box: See if the code works for every atom type.
	 */
	Renderer renderer= new Renderer();
	Controller controller = new Controller(renderer, null);
	@Test
	void mustReturnTrue() {
		renderer.addObject(new UIAtom("alpha"));
		controller.addObject(new Atom("alpha"));
		assertTrue(controller.getShootingObject().getType().equals("alpha")); //Black-box first item added.
		
		Random random= new Random();
		random.setSeed(2);
		AtomFactory.random= random;
		controller.switchAtom();
		
		assertTrue(controller.getShootingObject().getType().equals("sigma")); //Black-box switch atom for one iteration.
		
		controller.switchAtom();
		
		assertTrue(controller.getShootingObject().getType().equals("beta")); //Glass-box first iteration.
		
		controller.switchAtom();
		
		assertTrue(controller.getShootingObject().getType().equals("gamma")); //Glass-box second iteration.
		
	}
	

}
