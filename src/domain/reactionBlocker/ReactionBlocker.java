package domain.reactionBlocker;

import domain.GameObject;
import domain.MovementStrategy;

@SuppressWarnings("serial")
public class ReactionBlocker extends GameObject implements MovementStrategy{

	public double width;
	public double height;
	public double speed;
	public String blockerType;
	
	public ReactionBlocker(String blockerType) {
		super();
		this.x=getX();
		this.y=getY();
		this.rotationAngle= getRotationAngle();
		this.speed= getSpeed();
		this.blockerType= blockerType;
	}
	
	public double getWidth() {
		return width;
	}


	public void setWidth(double width) {
		this.width = width;
	}


	public double getHeight() {
		return height;
	}


	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public void move(double speed) {
		this.setY(this.getY() + speed);
	}

	@Override
	public void update() {
		move(getSpeed()/50);
	}
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return blockerType;
	}

	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return super.getSpeed();
	}

	@Override
	public void move(double x, double y, double velX, double velY) {
		// TODO Auto-generated method stub
		
	}
	
}
