package Test_Mehmet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import domain.Controller;
import domain.atom.Atom;
import domain.shooter.AtomShooter;
import ui.Renderer;
import ui.UIAtom;
import ui.UIShooter;

class ControllerRotateShooterTest {
	/*
	 * Black Box : Rotate shooter right, left and the 90 degree limit of both sides.
	 */
	Renderer renderer= new Renderer();
	Controller controller = new Controller(renderer, null);
	@Test
	void test() {
		//Initial settings
		renderer.addObject(new UIAtom("alpha"));
		controller.addObject(new Atom("alpha"));
		controller.addObject(new AtomShooter(""));
		renderer.addObject(new UIShooter(null));
		
		//Black-box rotate shooter right.
		controller.rotateShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "right");
		assertTrue(controller.getShooter().getRotationAngle()==controller.rotationConstant);
		
		//Black-box rotate shooter left.
		controller.rotateShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "left");
		assertTrue(controller.getShooter().getRotationAngle()==0);
		
		//Black-box rotate shooter left to the limit of 90 degrees. It should not go more than 90 degrees.
		controller.rotateShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "left");
		controller.rotateShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "left");
		controller.rotateShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "left");
		controller.rotateShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "left");
		controller.rotateShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "left");
		controller.rotateShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "left");
		controller.rotateShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "left");
		controller.rotateShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "left");
		controller.rotateShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "left");
		controller.rotateShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "left");
		//There is 10 iterations which means 100 degrees rotation. However, the shooter will not rotate after 90 degree limit.
		assertTrue(controller.getShooter().getRotationAngle()==-90);
		
		
		//Black-box rotate shooter left to the limit of 90 degrees. It should not go more than 90 degrees.
		controller.getShooter().setRotationAngle(90);
		controller.rotateShooter((AtomShooter) controller.getShooter(), controller.getShootingObject(), "right");
		assertTrue(controller.getShooter().getRotationAngle()==90);
	}

}
