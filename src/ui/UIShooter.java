package ui;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;



public class UIShooter extends UIGameObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String type;
	public int diameter;
	BufferedImage bimage;
	Image image;
	double width,height;
	AffineTransform at = new AffineTransform();
	double rotationAngle;
	



	public UIShooter(String type) {
		// TODO Auto-generated constructor stub
		super();
		this.type=type;
		this.width=getWidth();
		this.height=getHeight();
	}



	



	@Override
	public void render(Graphics2D g) {
		String file= "src/assets/shooter.png";
		ImageIcon icon = new ImageIcon(file);
		image = icon.getImage();
		at.setToIdentity();
		icon= new ImageIcon(image);
		image= icon.getImage();
		image= image.getScaledInstance((int)getWidth(), (int)getHeight(), Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		image = icon.getImage();
		double rotation = Math.toRadians(getRotationAngle());
		at.rotate(rotation,x,y + icon.getIconHeight());
		at.translate(x, y);
		
		g.drawImage(image, at, new Canvas());
		
	}

	public String getAtomType() {
		return type;
	}

	public void setAtomType(String type) {
		this.type = type;
	}
	
	public double getRotationAngle() {
		return rotationAngle;
	}



	public void setRotationAngle(double rotationAngle) {
		this.rotationAngle = rotationAngle;
	}
	
	public double getHeight() {
		return super.getHeight();
	}



	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return super.getWidth();
	}



	@Override
	public void setWidth(double width) {
		// TODO Auto-generated method stub
		super.setWidth(width);
	}



	@Override
	public void setHeight(double height) {
		// TODO Auto-generated method stub
		super.setHeight(height);
	}
}
