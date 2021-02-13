package domain.molecule;

import domain.atom.Atom;
import ui.KuVid;


public class GammaMolecule extends Molecule{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean hasReached = false;

	public GammaMolecule(){
		this.setWidth((int) (Molecule.L/4));
		this.setHeight((int) (Molecule.L/4));
	}

	@Override
	public void collectMolecule() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInDanger() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isIntersecting(Atom bullet) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "GammaMolecule";
	}
	@Override
	public void update() {
		move(getSpeed()/40);
		
	}
	

	@Override
	public void move(double speed) {
		if(this.getY() >= KuVid.HEIGHT / 2) {
			hasReached = true;
		}
		if(!hasReached) {
			this.setY(this.getY() + speed);
		}else {
			int a = rand.nextInt(100);

			if (a > 95) {
				super.i = rand.nextInt(2);
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
		
	}


	@Override
	public void move(double x, double y, double velX, double velY) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return super.getSpeed();
	}

}