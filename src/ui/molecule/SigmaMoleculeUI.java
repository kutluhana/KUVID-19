package ui.molecule;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

public class SigmaMoleculeUI extends UIMolecule{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	AffineTransform at = new AffineTransform();
	
	public SigmaMoleculeUI(){
		super();
		
	}
	
	public void paintComponent(Graphics2D g) {
		super.paintComponent(g);
		g.drawImage(image, (int)x,(int)y, (ImageObserver) this);
	}

	@Override
	public void render(Graphics2D g){
		
		ImageIcon icon = new ImageIcon("src/assets/molecules/sigma-.png");
		image= icon.getImage();
		image= image.getScaledInstance((int)getWidth(), (int)getHeight(), Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		image = icon.getImage();
//        at.setToIdentity();
//		at.translate(x,y);
		g.drawImage(image, (int) x,(int) y, new Canvas());
	}
}


