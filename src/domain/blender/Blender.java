package domain.blender;
import java.awt.Image;
import java.awt.image.BufferedImage;
import domain.GameObject;
import domain.atom.Atom;
import ui.KuVid;

public class Blender extends GameObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String atomType;
	String targetAtomType;
	BufferedImage bimage;
	Image image;
	int width;
	int height;
	final static int atomNumber= 100;
	final static int L=200;
	final static int diameter= L/10;
	public int alphaCount,betaCount,sigmaCount,gammaCount;

	public Blender() {
		this.width=L;
		this.height=L;

	}
	public void BlendAtom(int atomRank, int targetAtomRank) {
		if(atomRank == 1 && targetAtomRank == 2) { // this part modifies atom lists
			if(KuVid.alphaList.size() >= 2) {
				KuVid.alphaList.remove(KuVid.alphaList.size() - 1);
				KuVid.alphaList.remove(KuVid.alphaList.size() - 1);

				KuVid.betaList.add(new Atom("beta"));
				System.out.println("You have blended 2 alpha atoms to get a beta atom");
			} else {
				System.out.println("You don't have enough atoms to do this");
			}
			if(alphaCount >= 2) {       // this part modifies atom counts 
				alphaCount = alphaCount - 2;

				betaCount = betaCount + 1;
				System.out.println("You have blended 2 alpha atoms to get a beta atom");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
		}
		if(atomRank == 1 && targetAtomRank == 3) {
			if(KuVid.alphaList.size() >= 3) {
				KuVid.alphaList.remove(KuVid.alphaList.size() - 1);
				KuVid.alphaList.remove(KuVid.alphaList.size() - 1);
				KuVid.alphaList.remove(KuVid.alphaList.size() - 1);

				KuVid.gammaList.add(new Atom("gamma"));
				System.out.println("You have blended 3 alpha atoms to get a gamma atom");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
			if(alphaCount >= 3) {
				alphaCount = alphaCount - 3;

				gammaCount = gammaCount + 1;
				System.out.println("You have blended 3 alpha atoms to get a gamma atom");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
		}	
		if(atomRank == 1 && targetAtomRank == 4) {
			if(KuVid.alphaList.size() >= 4) {
				KuVid.alphaList.remove(KuVid.alphaList.size() - 1);
				KuVid.alphaList.remove(KuVid.alphaList.size() - 1);
				KuVid.alphaList.remove(KuVid.alphaList.size() - 1);
				KuVid.alphaList.remove(KuVid.alphaList.size() - 1);

				KuVid.sigmaList.add(new Atom("sigma"));
				System.out.println("You have blended 4 alpha atoms to get a sigma atom");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
			if(alphaCount >= 4) {
				alphaCount = alphaCount - 4;

				sigmaCount = sigmaCount + 1;
				System.out.println("You have blended 4 alpha atoms to get a sigma atom");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
		}
		if(atomRank == 2 && targetAtomRank == 3) {
			if(KuVid.betaList.size() >= 2) {
				KuVid.betaList.remove(KuVid.betaList.size() - 1);
				KuVid.betaList.remove(KuVid.betaList.size() - 1);

				KuVid.gammaList.add(new Atom("gamma"));
				System.out.println("You have blended 2 beta atoms to get a gamma atom");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
			if(betaCount >= 2) {
				betaCount = betaCount - 2;

				gammaCount = gammaCount + 1;
				System.out.println("You have blended 2 beta atoms to get a gamma atom");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
		}
		if(atomRank == 2 && targetAtomRank == 4) {
			if(KuVid.betaList.size() >= 3) {
				KuVid.betaList.remove(KuVid.betaList.size() - 1);
				KuVid.betaList.remove(KuVid.betaList.size() - 1);
				KuVid.betaList.remove(KuVid.betaList.size() - 1);

				KuVid.sigmaList.add(new Atom("sigma"));
				System.out.println("You have blended 3 beta atoms to get a sigma atom");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
			if(betaCount >= 3) {
				betaCount = betaCount - 3;

				sigmaCount = sigmaCount + 1;
				System.out.println("You have blended 3 beta atoms to get a sigma atom");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
		}
		if(atomRank == 3 && targetAtomRank == 4) {
			if(KuVid.gammaList.size() >= 2) {
				KuVid.gammaList.remove(KuVid.gammaList.size() - 1);
				KuVid.gammaList.remove(KuVid.gammaList.size() - 1);

				KuVid.sigmaList.add(new Atom("sigma"));
				System.out.println("You have blended 2 gamma atoms to get a sigma atom");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
			if(betaCount >= 2) {
				gammaCount = gammaCount - 2;

				sigmaCount = sigmaCount + 1;
				System.out.println("You have blended 2 gamma atoms to get a sigma atom");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
		}
	}
	public void BreakAtom(int atomRank, int targetAtomRank) {

		if(atomRank == 2 && targetAtomRank == 1) {
			if(KuVid.betaList.size() >= 1) {
				KuVid.betaList.remove(KuVid.betaList.size() - 1);

				KuVid.alphaList.add(new Atom("alpha"));
				KuVid.alphaList.add(new Atom("alpha"));
				System.out.println("You have broken a beta atom to get 2 alpha atoms");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
			if(betaCount >= 1) {
				betaCount = betaCount - 1;

				alphaCount = alphaCount + 2;
				System.out.println("You have broken a beta atom to get 2 alpha atoms");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
		}
		if(atomRank == 3 && targetAtomRank == 1) {
			if(KuVid.gammaList.size() >= 1) {
				KuVid.gammaList.remove(KuVid.gammaList.size() - 1);

				KuVid.alphaList.add(new Atom("alpha"));
				KuVid.alphaList.add(new Atom("alpha"));
				KuVid.alphaList.add(new Atom("alpha"));
				System.out.println("You have broken a gamma atom to get 3 alpha atoms");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
			if(gammaCount >= 1) {
				gammaCount = gammaCount - 1;

				alphaCount = alphaCount + 3;
				System.out.println("You have broken a gamma atom to get 3 alpha atoms");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
		}	
		if(atomRank == 4 && targetAtomRank == 1) {
			if(KuVid.sigmaList.size() >= 1) {
				KuVid.sigmaList.remove(KuVid.sigmaList.size() - 1);

				KuVid.alphaList.add(new Atom("alpha"));
				KuVid.alphaList.add(new Atom("alpha"));
				KuVid.alphaList.add(new Atom("alpha"));
				KuVid.alphaList.add(new Atom("alpha"));
				System.out.println("You have broken a sigma atom to get 4 alpha atoms");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
			if(gammaCount >= 1) {
				sigmaCount = sigmaCount - 1;

				alphaCount = alphaCount + 4;
				System.out.println("You have broken a sigma atom to get 4 alpha atoms");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
		}
		if(atomRank == 3 && targetAtomRank == 2) {
			if(KuVid.gammaList.size() >= 1) {
				KuVid.gammaList.remove(KuVid.gammaList.size() - 1);

				KuVid.betaList.add(new Atom("beta"));
				KuVid.betaList.add(new Atom("beta"));
				System.out.println("You have broken a gamma atom to get 2 beta atoms");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
			if(gammaCount >= 1) {
				gammaCount = gammaCount - 1;

				betaCount = betaCount + 2;
				System.out.println("You have broken a gamma atom to get 2 beta atoms");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
		}
		if(atomRank == 4 && targetAtomRank == 2) {
			if(KuVid.sigmaList.size() >= 1) {
				KuVid.sigmaList.remove(KuVid.sigmaList.size() - 1);

				KuVid.betaList.add(new Atom("beta"));
				KuVid.betaList.add(new Atom("beta"));
				KuVid.betaList.add(new Atom("beta"));
				System.out.println("You have broken a sigma atom to get 3 beta atoms");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
			if(sigmaCount >= 1) {
				sigmaCount = sigmaCount - 1;

				betaCount = betaCount + 3;
				System.out.println("You have broken a sigma atom to get 3 beta atoms");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
		}
		if(atomRank == 4 && targetAtomRank == 3) {
			if(KuVid.sigmaList.size() >= 1) {
				KuVid.sigmaList.remove(KuVid.sigmaList.size() - 1);

				KuVid.gammaList.add(new Atom("gamma"));
				KuVid.gammaList.add(new Atom("gamma"));
				System.out.println("You have broken a sigma atom to get 2 gamma atoms");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
			if(sigmaCount >= 1) {
				sigmaCount = sigmaCount - 1;

				gammaCount = gammaCount + 2;
				System.out.println("You have broken a sigma atom to get 2 gamma atoms");
			} else {
				System.out.println("You don't have enough atoms to do this");

			}
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void move(double x, double y, double velX, double velY) {
		// TODO Auto-generated method stub
		
	}


}
