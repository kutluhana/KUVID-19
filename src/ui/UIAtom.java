package ui;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;



/**
 * @author MehmetUstek
 *
 */
public class UIAtom extends UIGameObject{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String atomType;
	public double diameter;
	ImageIcon icon;
	Image image;
	double width,height;
//	AffineTransform at = new AffineTransform();
	public UIAtom(String atomType) {
		super();
		this.atomType=atomType;
		this.diameter= getDiameter();
		this.width=diameter*2;
		this.height=diameter*2;
	}

	
	public double getDiameter() {
		return diameter;
	}


	public void setDiameter(double diameter) {
		this.diameter = diameter;
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


	@Override
	public void render(Graphics2D g) {
		String file= "src/assets/atoms/"+ getAtomType() +".png";
		icon = new ImageIcon(file);
		image= icon.getImage();
		image= image.getScaledInstance((int)getWidth(), (int)getHeight(), Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		image = icon.getImage();
		g.drawImage(image,(int) x,(int) y,new Canvas());
		
		
	}
	public String getAtomType() {
		return atomType;
	}

	public void setAtomType(String atomType) {
		this.atomType = atomType;
	}
	


}
