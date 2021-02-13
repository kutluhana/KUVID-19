package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;


/**
 * @author MehmetUstek
 *
 */
public class Renderer {
	/**
	 * OVERVIEW: This class is a UI class that works on displaying every objects in the specified Frame.
	 * Every UI object has render() method that overrides UIGameObject class. We used this class where we add
	 * every element and for each element we call render() or update() methods from their class' structure.
	 */
	public List<UIGameObject> objects = new LinkedList<UIGameObject>();
	int lives;
	double score;
	boolean gameOver;
	public static double WIDTH= KuVid.WIDTH;
	public static double HEIGHT= KuVid.HEIGHT;
	
	public void render(Graphics2D g) {
		/**
		 * @modifies the ui objects's visibility in the frame
		 * @effects display every element which is added to the UI by using the object's render method.
		 */
		for (int i = 0; i < objects.size(); i++) {
			UIGameObject temp = (UIGameObject) objects.get(i);
			if(temp.getY()+temp.getHeight()/2>0) {
				temp.render(g);
			}
		}
		g.setColor(Color.black);
		g.setFont(new Font("TimesRoman",Font.PLAIN,22));
		if(gameOver) {
			g.setFont(new Font("TimesRoman",Font.PLAIN,48));
			g.drawString("GAME OVER", (int) WIDTH/2 - 160,(int)  HEIGHT/2 - 24);
		}
	}

	public void addObject(UIGameObject obj) {
		this.objects.add(obj);
	}

	public void removeObject(UIGameObject obj) {
		this.objects.remove(obj);
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
}