package Test_Mehmet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import domain.atom.Atom;

class AtomMoveTest {
	/*
	 * Black box: Atom parallel to the ground, left and right movement and,
	 * Atom vertical to the ground, going up and down.
	 * Glass box: Atom is in the 37 degrees vertical to the horizon. 
	 */
	@Test
	void mustReturnTrue() {
		Atom atom = new Atom("alpha");
		atom.setX(10);
		atom.setY(10);
		atom.setSpeed(10);
		atom.setRotationAngle(90);
		double velX= atom.getSpeed() * Math.sin(Math.toRadians(atom.getRotationAngle()));
		double velY= atom.getSpeed() * Math.cos(Math.toRadians(180-atom.getRotationAngle()));
		
		// Black Box -- Atom is shooted parallel to the ground -- going right
		atom.move(atom.getX(), atom.getY(), velX, velY);
		assertTrue(atom.getX()==20);
		assertTrue(atom.getY()==10);
		
		//Black-Box  -- Atom is shooted parallel to the ground - going left
		atom.setRotationAngle(-90);
		velX= atom.getSpeed() * Math.sin(Math.toRadians(atom.getRotationAngle()));
		velY= atom.getSpeed() * Math.cos(Math.toRadians(180-atom.getRotationAngle()));
		atom.move(atom.getX(), atom.getY(), velX, Math.ceil(velY));
		assertTrue(atom.getX()==10);
		assertTrue(atom.getY()==10);
		
		
		//Black Box -- Atom is shooted vertical to the ground -- going up
		atom.setX(10);
		atom.setY(10);
		atom.setRotationAngle(0);
		velX= atom.getSpeed() * Math.sin(Math.toRadians(atom.getRotationAngle()));
		velY= atom.getSpeed() * Math.cos(Math.toRadians(180-atom.getRotationAngle()));
		atom.move(atom.getX(), atom.getY(), velX, velY);
		assertTrue(atom.getX()==10);
		assertTrue(atom.getY()==0);
		
		//Black Box -- Atom is shooted vertical to the ground -- going down
		atom.setRotationAngle(180);
		velX= atom.getSpeed() * Math.sin(Math.toRadians(atom.getRotationAngle()));
		velY= atom.getSpeed() * Math.cos(Math.toRadians(180-atom.getRotationAngle()));
		atom.move(atom.getX(), atom.getY(), Math.floor(velX), velY);
		assertTrue(atom.getX()==10);
		assertTrue(atom.getY()==10);
		
		//Glass-Box -- Atom is shooted in a 37 degree to the horizon. The 37 degree is special degree that forms 3-4-5 triangle.
		// Atom will go in right up direction. In this case, the atom will go upwards in the frame decreasing y, increasing x.
		atom.setRotationAngle(53);
		velX= atom.getSpeed() * Math.sin(Math.toRadians(atom.getRotationAngle()));
		velY= atom.getSpeed() * Math.cos(Math.toRadians(180-atom.getRotationAngle()));
		atom.move(atom.getX(), atom.getY(), Math.ceil(velX), Math.ceil(velY));
		assertTrue(atom.getX()==18);
		assertTrue(atom.getY()==4);
	}

}
