package ui;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;



@SuppressWarnings("serial")
public class UIReactionBlocker extends UIGameObject implements ImageObserver{	
	
	public String blockerType;
	public double diameter;
	ImageIcon icon;
	Image image;
	double width,height;
//	AffineTransform at = new AffineTransform();
	
	public UIReactionBlocker(String blockerType) {
		// TODO Auto-generated constructor stub
		super();
		this.blockerType=blockerType;
		this.diameter= getDiameter();
		this.width=diameter;
		this.height=diameter;
	}

	
	public double getDiameter() {
		return diameter;
	}


	public void setDiameter(double diameter) {
		this.diameter = diameter;
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
		String file= "src/assets/blockers/"+ getBlockerType() +"-b.png";
		icon = new ImageIcon(file);
		image = icon.getImage();
		image= image.getScaledInstance((int)getWidth(), (int)getHeight(), Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		image = icon.getImage();
		Rectangle2D r= new Rectangle2D.Double(x,y,getWidth(),getHeight());
        double cx= r.getCenterX();
        double cy= r.getCenterY();
//        at.setToIdentity();
//		at.translate(cx,cy);
        g.drawImage(image, (int) cx,(int) cy, new Canvas());
		
	}
	public String getBlockerType() {
		return blockerType;
	}

	public void setBlockerType(String blockerType) {
		this.blockerType = blockerType;
	}
	

	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}

}
