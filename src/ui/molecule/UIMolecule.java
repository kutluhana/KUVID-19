package ui.molecule;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import ui.UIGameObject;

public class UIMolecule extends UIGameObject implements ImageObserver{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage bimage;
	Image image;
	public double L; 
	public double width;
	public double height;
	
	public UIMolecule(){
		super();
	}
	
	
	public void render(Graphics2D g){
		
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
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

}
