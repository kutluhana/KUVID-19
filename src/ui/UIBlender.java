package ui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;



public class UIBlender extends UIGameObject implements ImageObserver{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String atomType;
	public double diameter;
	BufferedImage bimage;
	Image image;
	int width,height;
	double x,y;
	AffineTransform at = new AffineTransform();
	public UIBlender(int w, int h) {
		// TODO Auto-generated constructor stub
		super();
		this.width=w;
		this.height=h;
	}


	@Override
	public void render(Graphics2D g) {
		String file= "src/assets/mixer.png";
		ImageIcon icon = new ImageIcon(file);
		image = icon.getImage();
		at.setToIdentity();
		at.translate(x, y);
		g.drawImage(image, (int) x, (int) y , width, height, this);
		
	}
	@Override
	public void setX(double x) {
		this.x = x;
	}
	@Override
	public void setY(double y) {
		this.y = y;
	}
	
	public void paintComponent(Graphics2D g) {
		render(g);
	}

	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}

}
