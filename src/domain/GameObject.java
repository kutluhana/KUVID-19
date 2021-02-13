package domain;

import java.io.Serializable;

/**
 * @author MehmetUstek
 *
 */
@SuppressWarnings("serial")
public abstract class GameObject implements Serializable {

	protected double x, y, velX, velY, rotationAngle,width,height;
	

	String type;
	double speed;
	boolean isShooted;

	public GameObject() {
		this.x = getX();
		this.y = getY();
	}
	public abstract void move(double x,double y,double velX,double velY);
	public boolean isShooted() {
		return isShooted;
	}

	public void setShooted(boolean isShooted) {
		this.isShooted = isShooted;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public abstract void update();
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public double getRotationAngle() {
		return rotationAngle;
	}

	public void setRotationAngle(double rotationAngle) {
		this.rotationAngle = rotationAngle;
	}

	public void setY(double y) {
		this.y = y;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double vely) {
		this.velY = vely;
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

}
