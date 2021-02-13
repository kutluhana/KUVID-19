package domain;

import java.awt.Dimension;
import java.util.TimerTask;

import domain.shooter.AtomShooter;

/**
 * @author MehmetUstek
 *
 */
public class UpdateAtomTask extends TimerTask {
	/**
	 * OVERVIEW: This class is a domain class that updates atom's positions and angles once an atom is shooted.
	 * It extends TimerTask class to enable multi timing of the frame.
	 */
    GameObject atom;
    Dimension d;
    double width;
    AtomShooter shooter;
    double velX;
	double velY;
	double x;
	double y;
    public UpdateAtomTask(GameObject atom,Dimension d,AtomShooter shooter) {
    	 this.atom= atom;
    	 this.d = d;
    	 this.shooter= shooter;
    	 width= d.getWidth()-200;
    	 velX= atom.getSpeed() * Math.sin(Math.toRadians(atom.getRotationAngle()));
    	 velY= atom.getSpeed() * Math.cos(Math.toRadians(180-atom.getRotationAngle()));
    }
    @Override
    public void run() {
    	/**
		 * @requires a valid and initialized shooting object and AtomShooter.
		 * @modifies the shooting object's positions, angles.
		 * @effects update the position and angle of a shooted object regarding its speed, place in the game's legal positions etc.
		 */
    	if(!atom.isShooted()) {
    		this.cancel();
    	}
    	if(atom.getY()> d.getHeight()+atom.getHeight()/2) {
    		atom.setShooted(false);
    		
    		this.cancel();
    		
    	}
    	else if(atom.getY() <0) {
    		if(atom.getRotationAngle()==0) {
	    		atom.setRotationAngle(atom.getRotationAngle()-180);
	    		velY = -velY;
	    		atom.move(atom.getX(),atom.getY(),velX,velY);
    		}
    		else if(atom.getRotationAngle()<0) {
    			atom.setRotationAngle(180+atom.getRotationAngle());
    			velY= -velY;
        		atom.move(atom.getX(),atom.getY(),velX,velY);
    		}
    		else {
    			atom.setRotationAngle(180-atom.getRotationAngle());
    			velY= -velY;
        		atom.move(atom.getX(),atom.getY(),velX,velY);
    		}
    	}
    	else if(atom.getX() > 0 && atom.getY() > 0 && atom.getX()< width - atom.getWidth()*2) {
    		
    		atom.move(atom.getX(),atom.getY(),velX,velY);
    	}
    	else if(atom.getX() < atom.getWidth()/2) {
//    		atom.setRotationAngle(atom.getRotationAngle()+90);
    		atom.setRotationAngle(-atom.getRotationAngle());
    		velX = -velX;
    		atom.move(atom.getX(),atom.getY(),velX,velY);
    	}
    	else if( atom.getX() >= width - atom.getWidth()*2) {
    		velX = -velX;
    		atom.setRotationAngle(-atom.getRotationAngle());
    		atom.move(atom.getX(),atom.getY(),velX,velY);
    	}
    	else {
    		atom.move(atom.getX(),atom.getY(),velX,velY);

    	}
    }

}
 