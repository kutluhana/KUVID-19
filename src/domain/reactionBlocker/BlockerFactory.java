package domain.reactionBlocker;
import java.util.Random;


public class BlockerFactory {
	static Random random = new Random();
	private static ReactionBlocker blocker;
	public static ReactionBlocker getBlocker(ReactionBlocker blocker,String type) {
		blocker.setType(type);
		return blocker;
	}
	public static ReactionBlocker getBlocker() {
		int i = random.nextInt(4);
		switch(i) {
		case 0:
			blocker= new AlphaBlocker("alpha");
			break;
		case 1:
			blocker= new BetaBlocker("beta");
			break;
		case 2:
			blocker= new SigmaBlocker("sigma");
			break;
		case 3:
			blocker= new GammaBlocker("gamma");
			break;
		}
		return blocker;
	}
	public static ReactionBlocker getBlocker(String type) {
		if(type.equals("alpha")) {
			blocker = new AlphaBlocker(type);
		}
		else if(type.equals("beta")) {
			blocker = new BetaBlocker(type);
		}
		else if(type.equals("sigma")) {
			blocker = new SigmaBlocker(type);
		}
		else if(type.equals("gamma")) {
			blocker = new GammaBlocker(type);
		}
		return blocker;
	}	
}
