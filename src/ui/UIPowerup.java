package ui;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * @author MehmetUstek
 *
 */
public class UIPowerup extends UIGameObject{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String puType;
	ImageIcon icon;
	Image image;
	double width,height;
	public UIPowerup(String puType) {
		// TODO Auto-generated constructor stub
		super();
		this.puType=puType;
		this.width=getWidth();
		this.height=getHeight();
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
	public void render(Graphics2D g) {
		String file= "src/assets/powerups/"+ getPUType() +"-b.png";
		icon = new ImageIcon(file);
		image = icon.getImage();
		image= image.getScaledInstance((int)getWidth(), (int)getHeight(), Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		image = icon.getImage();
		g.drawImage(image, (int) x,(int) y, new Canvas());
		
	}
	public String getPUType() {
		return puType;
	}

	public void setPUType(String atomType) {
		this.puType = atomType;
	}
	

}
