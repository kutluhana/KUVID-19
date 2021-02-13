package domain.molecule;

public class MoleculeFactory {
	
	public static Molecule getMolecule(String molecule) {
		Molecule mol = null;
		
		if(molecule.equals("AlphaMolecule")){
			mol = new AlphaMolecule();
			return mol;
		}
		
		if(molecule.equals("BetaMolecule")){
			mol = new BetaMolecule();
			return mol;
		}
		
		if(molecule.equals("GammaMolecule")){
			mol = new GammaMolecule();
			return mol;
		}
		
		if(molecule.equals("SigmaMolecule")){
			mol = new SigmaMolecule();
			return mol;
		}
		return null;
	}

}
