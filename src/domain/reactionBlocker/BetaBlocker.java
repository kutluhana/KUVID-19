package domain.reactionBlocker;

import java.util.Random;

import ui.KuVid;

public class BetaBlocker extends ReactionBlocker{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static boolean hasReached = false;
	Random rand = new Random();
	int i = 0;
	public BetaBlocker(String blockerType) {
		super(blockerType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(double speed) {
		if(this.getY() >= KuVid.HEIGHT / 4) {
			hasReached = true;
		}
		if(!hasReached) {
			this.setY(this.getY() + speed);
		}else {
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
	}

	@Override
	public void update() {
		move(getSpeed()/40);
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "beta";
	}

	
	
	

}
