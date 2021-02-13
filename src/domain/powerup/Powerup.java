package domain.powerup;

import domain.GameObject;

/**
 * @author MehmetUstek
 *
 */
public class Powerup extends GameObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String puType;
	double rotationAngle;
	double speed;
	double diameter;
	double x,y;
	boolean isShooted;
	
	public Powerup(String puType){
		super();
		this.x=getX();
		this.y=getY();
		this.rotationAngle= getRotationAngle();
		this.speed= getSpeed();
		this.diameter= getDiameter();
		this.puType= puType;
	}

	public boolean isShooted() {
		return isShooted;
	}
	public void setShooted(boolean isShooted) {
		this.isShooted = isShooted;
	}
	
	public double getDiameter() {
		return diameter;
	}
	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}
	
	
	public String getType() {
		return puType;
	}
	public void setType(String puType) {
		this.puType = puType;
	}
	public double getRotationAngle() {
		return rotationAngle;
	}
	public void setRotationAngle(double rotationAngle) {
		this.rotationAngle = rotationAngle;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public void move(double x, double y,double velX, double velY) {
		double newX=  x+velX;
		double newY=  y +velY;
		setX(newX);
		setY(newY);
	}
	public void fallInStraightLine(double x, double y) {
		double newY=  y + getSpeed()/40;
		setY(newY);
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
