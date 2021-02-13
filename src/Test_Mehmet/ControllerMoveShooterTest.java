package Test_Mehmet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import domain.Controller;
import domain.atom.Atom;
import domain.shooter.AtomShooter;
import ui.Renderer;
import ui.UIAtom;
import ui.UIShooter;

class ControllerMoveShooterTest {
	Renderer renderer= new Renderer();
	Controller controller = new Controller(renderer, null);
	@Test
	void test() {
		//Initial settings
		renderer.addObject(new UIAtom("alpha"));
		controller.addObject(new Atom("alpha"));
		controller.addObject(new AtomShooter(""));
		renderer.addObject(new UIShooter(null));
		controller.moveShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "right");
		
		//Set shooter's position (x) to 10.
		controller.setShooterX(0);
		controller.moveShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "right");
		//Black-box move shooter to the right by one unit of shooter moving (which is 15)
		assertTrue(controller.getShooterX()==15);
		
		controller.moveShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "left");
		//Black-box move shooter to the left by one unit of shooter moving (shooter has to come to the initial position.
		assertTrue(controller.getShooterX()==0);
		
		//Glass-box move shooter 4 times to the right.
		controller.moveShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "right");
		controller.moveShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "right");
		controller.moveShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "right");
		controller.moveShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "right");
		//It needs to go 60 units. (15x4)
		assertTrue(controller.getShooterX()==60);
		
		//Glass-box move shooter 5 times to the left.
		controller.moveShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "left");
		controller.moveShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "left");
		controller.moveShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "left");
		controller.moveShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "left");
		// It needs to go 60 units left which is the 0 point again.
		assertTrue(controller.getShooterX()==0);
	}

}
