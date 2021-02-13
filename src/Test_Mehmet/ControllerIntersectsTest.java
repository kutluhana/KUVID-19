package Test_Mehmet;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.geom.Rectangle2D;

import org.junit.jupiter.api.Test;

import domain.Controller;

class ControllerIntersectsTest {
	/*
	 * Black-box: Left and right corner and overlapping rectangles black-box testing.
	 * Glass-box testing: show there is correlation that whether the objects change places it will give the same result.
	 */

	@Test
	void mustBeTrue() {
		Rectangle2D a= new Rectangle2D.Double(10,10,10,10);
		// a rectangle with width 10 height 10 positioned at 10,10.
		Rectangle2D b= new Rectangle2D.Double(0,0,15,15);
		// a rectangle with width 15 height 15 positioned at 0,0.
		//Their widths and heights are different and positions are different.
		assertTrue(Controller.intersects(a,b)); //Black-box left bottom corner collision
		
		a= new Rectangle2D.Double(92,92,10,10);
		b= new Rectangle2D.Double(100,100,10,10);
		//Their widths and heights are the same but positions are different.
		assertTrue(Controller.intersects(a,b)); //Black-box inside rectangle collision
		
		a= new Rectangle2D.Double(100,100,20,20);
		b= new Rectangle2D.Double(100,100,10,10);
		//Their positions are the same but width and heights are different.
		assertTrue(Controller.intersects(a,b)); //Black-box overlapping rectangle with different bounds.
		
		a= new Rectangle2D.Double(100,100,10,10);
		b= new Rectangle2D.Double(100,100,10,10);
		//They are the the same rectangles at the same point.
		assertTrue(Controller.intersects(a,b)); //Black-box overlapping rectangle with same bounds.
		
		a= new Rectangle2D.Double(100,100,15,15);
		b= new Rectangle2D.Double(110,110,10,10);
		// the second rectangle is positioned at the right of the first rectangle.
		assertTrue(Controller.intersects(a,b)); //Black-box right bottom corner collision
	}
	
	@Test
	void mustBeFalse() {
		Rectangle2D a= new Rectangle2D.Double(100,100,20,20);
		Rectangle2D b= new Rectangle2D.Double(80,80,20,20);
		
		//They are tangent to each other. They do not touch but they are 1 unit away from touching
		// or only their edges are touching.
		assertFalse(Controller.intersects(a,b)); //Black-box left-bottom tangent rectangles.
		
		a= new Rectangle2D.Double(100,100,20,20);
		b=new Rectangle2D.Double(100,80,20,20);
		//They are positioned at one is top and one is directly below it.
		// their edges overlap but do not intersect.
		assertFalse(Controller.intersects(a,b)); //Black-box up-down tangent rectangles.
		
		b= new Rectangle2D.Double(100,100,20,20);
		a=new Rectangle2D.Double(100,80,20,20);
		//The previous test's a and b have switched positions
		assertFalse(Controller.intersects(a,b)); //Glass-box up-down tangent rectangles with switched positions of previous test.
		
	}

}
