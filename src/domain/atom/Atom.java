package domain.atom;

import java.util.Random;

import domain.GameObject;

/**
 * @author MehmetUstek
 *
 */
public class Atom extends GameObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String atomType;
	double rotationAngle;
	double speed;
	double diameter;
	double x,y;
	boolean isShooted;
	double stability,efficiency;
	int protons, neutrons;
	double width,height;
	public Atom(String atomType){
		super();
		this.x=getX();
		this.y=getY();
		this.rotationAngle= getRotationAngle();
		this.speed= getSpeed();
		this.diameter= getDiameter();
		this.atomType= atomType;
//		this.stability= getStability();
		this.protons = getProtons();
		this.neutrons= getNeutrons();
		this.width= getDiameter();
		this.height= getDiameter();
		setEfficiency();
	}
	public double getWidth() {
		return diameter*2;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return diameter*2;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public int getProtons() {
		if(getType().equals("alpha")) {
			return 8;
		}
		else if(getType().equals("beta")) {
			return 16;
		}
		else if(getType().equals("sigma")) {
			return 32;
		}
		else {
			return 64;
		}
	}


	public int getNeutrons() {
		Random random = new Random();
		
		if(getType().equals("alpha")) {
			int number=random.nextInt(3);
			switch(number) {
			case 0:
				return 7;
			case 1:
				return 8;
			case 2:
				return 9;
			}
			
		}
		else if(getType().equals("beta")) {
			int number=random.nextInt(5);
			switch(number) {
			case 0:
				return 15;
			case 1:
				return 16;
			case 2:
				return 17;
			case 3:
				return 18;
			case 4:
				return 21;
			}
		}
		else if(getType().equals("sigma")) {
			int number=random.nextInt(3);
			switch(number) {
			case 0:
				return 29;
			case 1:
				return 32;
			case 2:
				return 33;
			}
		}
		else {
			int number=random.nextInt(3);
			switch(number) {
			case 0:
				return 63;
			case 1:
				return 64;
			case 2:
				return 67;
			}
		}
		return neutrons;
	}

	public void setNeutrons(int neutrons) {
		this.neutrons = neutrons;
	}
	

	public double getEfficiency() {
		return efficiency;
	}

	public void setEfficiency() {
		if(getType().equals("alpha")) {
			this.efficiency = (1- (Math.abs((getNeutrons()-getProtons())) / getProtons()) ) * 0.85;
		}
		if(getType().equals("beta")) {
			this.efficiency = 0.9 - (0.5 * Math.abs(getNeutrons() - getProtons()) / getProtons());
		}
		if(getType().equals("sigma")) {
			this.efficiency = (1+ 0.8) /2 +  (Math.abs(getNeutrons() - getProtons()) / getProtons());
		}
		if(getType().equals("gamma")) {
			this.efficiency = 0.7 + (Math.abs(getNeutrons() - getProtons()) / (2* getProtons()));
		}
	}

	public boolean isShooted() {
		return isShooted;
	}
	public void setShooted(boolean isShooted) {
		this.isShooted = isShooted;
	}
	@Override
	public String toString() {
		return atomType;
	}

	public double getDiameter() {
		return diameter;
	}
	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}
	
	
	
	public String getType() {
		return atomType;
	}
	public void setType(String atomType) {
		this.atomType = atomType;
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
