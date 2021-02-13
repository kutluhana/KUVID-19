package ui;

import java.util.Random;

public class UIBlockerFactory {
	static Random random = new Random();
	private static UIReactionBlocker blocker;
	
	public static UIReactionBlocker getBlocker() {
		int i = random.nextInt(4);
		switch(i) {
		case 0:
			blocker= new UIReactionBlocker("alpha");
			break;
		case 1:
			blocker= new UIReactionBlocker("beta");
			break;
		case 2:
			blocker= new UIReactionBlocker("sigma");
			break;
		case 3:
			blocker= new UIReactionBlocker("gamma");
			break;
		}
		return blocker;
	}
	public static UIReactionBlocker getBlocker(String type) {
		blocker = new UIReactionBlocker(type);
		return blocker;
	}	


}
