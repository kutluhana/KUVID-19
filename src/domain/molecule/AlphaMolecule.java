package domain.molecule;

import java.util.Random;
import domain.atom.Atom;

public class AlphaMolecule extends Molecule{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Random rand = new Random();
	int i = 0;
	public AlphaMolecule(){
		this.setWidth((int) (Molecule.L/4));
		this.setHeight((int) (Molecule.L/4));
	}

	@Override
	public void collectMolecule() {
		// TODO Auto-generated method stub	
	}
	
	@Override
	public boolean isInDanger() {
		return false;
	}

	@Override
	public boolean isIntersecting(Atom bullet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update() {
		move(getSpeed()/40);
	}
	
	@Override
	public void move(double speed) {
		int a = rand.nextInt(100);

		if (a > 95) {
			i = rand.nextInt(2);
			if (i == 0)
				this.setX(this.getX() - speed);
			else
				this.setX(this.getX() + speed);
		}
		else {
			if(i == 0) 
				this.setX(this.getX() - speed);
			else
				this.setX(this.getX() + speed);	
		}
		this.setY(this.getY() + speed);
	}


	@Override
	public void move(double x, double y, double velX, double velY) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "AlphaMolecule";
	}

	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return super.getSpeed();
	}
}
