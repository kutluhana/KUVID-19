package domain.shield;

import domain.atom.Atom;

public abstract class ShieldDecorator extends Atom{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ShieldDecorator(String atomType) {
		super(atomType);
		// TODO Auto-generated constructor stub
	}
	public abstract double getEfficiency();
}
