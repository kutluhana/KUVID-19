package domain.reactionBlocker;

import java.util.Random;

public class AlphaBlocker extends ReactionBlocker {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Random rand = new Random();
	int i = 0;
	public AlphaBlocker(String blockerType) {
		super(blockerType);
	}

	@Override
	public void move(double speed) {
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

	@Override
	public void update() {
		// TODO Auto-generated method stub
		move(getSpeed()/40);
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "alpha";
	}

}
