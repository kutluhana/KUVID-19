package domain.shield;
import domain.atom.Atom;

public class Theta extends ShieldDecorator{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Atom atom;
	double low = 0.05;
	double high = 0.15;
	
	double thetaEfficiencyBoost = Math.random() * (high - low) + low;
	
	public Theta(Atom atom) {
		super(atom.getType());
		this.atom = atom;
		setType(atom.getType());
		setX(atom.getX());
		setY(atom.getY());
		setSpeed(atom.getSpeed() * 91/100);
		setDiameter(atom.getDiameter());
	}

	@Override
	public double getEfficiency() {
		// TODO Auto-generated method stub
		return atom.getEfficiency() + (1 - atom.getEfficiency()) * thetaEfficiencyBoost;
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
	public double getHeight() {
		// TODO Auto-generated method stub
		return super.getHeight();
	}

	@Override
	public void setHeight(double height) {
		// TODO Auto-generated method stub
		super.setHeight(height);
	}

	@Override
	public int getProtons() {
		// TODO Auto-generated method stub
		return super.getProtons();
	}

	@Override
	public int getNeutrons() {
		// TODO Auto-generated method stub
		return super.getNeutrons();
	}

	@Override
	public void setNeutrons(int neutrons) {
		// TODO Auto-generated method stub
		super.setNeutrons(neutrons);
	}

	@Override
	public void setEfficiency() {
		// TODO Auto-generated method stub
		super.setEfficiency();
	}

	@Override
	public boolean isShooted() {
		// TODO Auto-generated method stub
		return super.isShooted();
	}

	@Override
	public void setShooted(boolean isShooted) {
		// TODO Auto-generated method stub
		super.setShooted(isShooted);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public double getDiameter() {
		// TODO Auto-generated method stub
		return super.getDiameter();
	}

	@Override
	public void setDiameter(double diameter) {
		// TODO Auto-generated method stub
		super.setDiameter(diameter);
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return super.getType();
	}

	@Override
	public void setType(String atomType) {
		// TODO Auto-generated method stub
		super.setType(atomType);
	}

	@Override
	public double getRotationAngle() {
		// TODO Auto-generated method stub
		return super.getRotationAngle();
	}

	@Override
	public void setRotationAngle(double rotationAngle) {
		// TODO Auto-generated method stub
		super.setRotationAngle(rotationAngle);
	}

	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return super.getSpeed();
	}

	@Override
	public void setSpeed(double speed) {
		// TODO Auto-generated method stub
		super.setSpeed(speed);
	}

	@Override
	public void move(double x, double y, double velX, double velY) {
		// TODO Auto-generated method stub
		super.move(x, y, velX, velY);
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return super.getX();
	}

	@Override
	public void setX(double x) {
		// TODO Auto-generated method stub
		super.setX(x);
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return super.getY();
	}

	@Override
	public void setY(double y) {
		// TODO Auto-generated method stub
		super.setY(y);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}
	
}
