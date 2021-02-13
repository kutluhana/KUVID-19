package Test_Mehmet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import domain.Controller;
import domain.SaveFile;
import domain.atom.Atom;
import ui.Renderer;
import ui.UIAtom;

class SaveLoadTest {
	/*
	 * Black-box: Test of the specified initial settings of the game.
	 * Glass-box: New iteration through the controller and saving the game again.
	 */
	Renderer renderer= new Renderer();
	Controller controller = new Controller(renderer, null);
	SaveFile save= new SaveFile(controller);
	@Test
	void mustBeTrue() {
		// I will use savetest.txt file for this test classes.
		// The initializing process below is a must to initialize a save object.
		renderer.addObject(new UIAtom("alpha"));
		controller.setUsername("savetest");
		controller.addObject(new Atom("alpha"));
		controller.loadGame();
		
		
		assertTrue(controller.getShootingObject().getType().equals("alpha")); //Black-box return the initial settings.
		
		//The set alpha count for saved file "savetest" contains 20 alpha atoms.
		assertTrue(controller.getAlphaCount()==20);//Black-box return the initial settings.
		
		//Test shield count
		assertTrue(controller.getEtaCount()==20);//Black-box return the initial settings.
		
		// Test powerup count
		assertTrue(controller.getAlphaPUCount()==20);//Black-box return the initial settings.
		
		// Test molecule count
		assertTrue(controller.objects.get(2).getType().equals("AlphaMolecule"));//Black-box return the initial settings for molecules.
		
		//Test time
		assertTrue(controller.getTime()==600);//Black-box return the initial settings.
		
		//Test difficulty / speed of the game TODO
		assertTrue(controller.getSpeed()==30);//Black-box return the initial settings.
		
		//Test username
		assertTrue(controller.getUsername().equals("savetest"));//Black-box return the initial settings.
		
		//Test beta atom
		assertTrue(controller.getBetaCount()==20);//Black-box return the initial settings for beta atom.

		//Test sigma atom
		assertTrue(controller.getSigmaCount()==20);//Black-box return the initial settings for sigma atom.

		//Test gamma atom
		assertTrue(controller.getGammaCount()==20);//Black-box return the initial settings for gamma atom.
		
		//Test lota shield count
		assertTrue(controller.getLotaCount()==20);//Black-box return the initial settings for lota shield.
		
		//Test theta shield count
		assertTrue(controller.getThetaCount()==20);//Black-box return the initial settings for theta shield.
		
		//Test zeta shield count
		assertTrue(controller.getZetaCount()==20);//Black-box return the initial settings for zeta shield.
	}
	
	

}
