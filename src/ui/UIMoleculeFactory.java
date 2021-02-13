package ui;

import ui.molecule.*;

public class UIMoleculeFactory {
	
	public static UIMolecule getMolecule(String molecule) {
		UIMolecule mol = null;
		
		if(molecule.equals("AlphaMolecule")){
			mol = new AlphaMoleculeUI();
			return mol;
		}
		
		if(molecule.equals("BetaMolecule")){
			mol = new BetaMoleculeUI();
			return mol;
		}
		
		if(molecule.equals("GammaMolecule")){
			mol = new GammaMoleculeUI();
			return mol;
		}
		
		if(molecule.equals("SigmaMolecule")){
			mol = new SigmaMoleculeUI();
			return mol;
		}
		return null;
	}

}
