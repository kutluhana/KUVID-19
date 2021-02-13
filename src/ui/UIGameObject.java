package ui;

import java.awt.Graphics2D;
import java.io.Serializable;

/**
 * @author MehmetUstek
 *
 */
public abstract class UIGameObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected double x, y;
	double width,height;

	public UIGameObject() {
		this.x = getX();
		this.y = getY();
		this.width= getWidth();
		this.height= getHeight();
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



	public abstract void render(Graphics2D g);

	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return  x;
	}

	public double getY() {
		return y;
	}
	public void paintComponent(Graphics2D g) {
		render(g);
	}

}
