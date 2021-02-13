package domain.molecule;

import domain.atom.Atom;


public class SigmaMolecule extends Molecule{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SigmaMolecule(){
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
		return "SigmaMolecule";
	}
	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return super.getSpeed();
	}
	@Override
	public void update() {
		move(getSpeed()/40);
		
	}
	
	@Override
	public void move(double speed) {
		this.setY(this.getY() + speed);
	}

	@Override
	public void move(double x, double y, double velX, double velY) {
		// TODO Auto-generated method stub
		
	}
}