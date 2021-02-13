package Test_Mehmet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import domain.Controller;
import domain.powerup.Powerup;
import domain.powerup.PowerupFactory;

class PowerupFactoryTest {
	/*
	 * Black-Box: Check returned powerup type's correctness.
	 */
	@Test
	void mustBeTrue() {
		Powerup pu= new Powerup("alpha");
		//PowerupFactory with unspecified type. This has to return random type of powerup.
		Powerup testPU= PowerupFactory.getPU(pu, "+alpha");
		assertTrue(Controller.isPowerup(testPU)); //Black-box: given an alpha powerup, returns an powerup with valid type.
		
		testPU = PowerupFactory.getPU(pu, "+alpha");
		assertTrue(testPU.getType().equals("+alpha")); //Black-box: given an alpha powerup, returns an alpha powerup.
		
		testPU = PowerupFactory.getPU(pu, "+beta");
		assertTrue(testPU.getType().equals("+beta")); //Black-box: given an beta powerup, returns an beta powerup.
		
		testPU = PowerupFactory.getPU(pu, "+sigma");
		assertTrue(testPU.getType().equals("+sigma")); //Black-box: given an sigma powerup, returns an sigma powerup.
		
		testPU = PowerupFactory.getPU(pu, "+gamma");
		assertTrue(testPU.getType().equals("+gamma")); //Black-box: given an gamma powerup, returns an gamma powerup.
	}

}
