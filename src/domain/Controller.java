package domain;

import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import domain.atom.Atom;
import domain.atom.AtomFactory;
import domain.blender.Blender;
import domain.molecule.Molecule;
import domain.powerup.Powerup;
import domain.reactionBlocker.ReactionBlocker;
import domain.shield.*;
import domain.shooter.AtomShooter;
import ui.Frame;
import ui.KuVid;
import ui.UIAtom;
import ui.UIGameObject;
import ui.UIPowerup;
import ui.UIReactionBlocker;
import ui.Renderer;
import ui.StatisticsWindow;
import ui.UIShooter;
import ui.molecule.UIMolecule;

/**
 * @author MehmetUstek
 *
 */
public class Controller {
	/**
	 * OVERVIEW: This class is a bridge class who connects every ui and domain element. Without this class, the ui object will not know the
	 * domain objects and vice versa. Every player action came from ui class is evaluated in this class.
	 */
	
	public static double WIDTH =  Toolkit.getDefaultToolkit().getScreenSize().getWidth()-200,
			HEIGHT =  Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	Renderer renderer;
	private int time, lives = 3, initialMoleculeCount,health=100;
	private double score= 0;
	private int alphaCount=100,betaCount=100,sigmaCount=100,gammaCount=100;
	private int alphaPUCount=2,betaPUCount=2,sigmaPUCount=2,gammaPUCount=2;
	private int etaCount=20,lotaCount=20,thetaCount=20,zetaCount=20;
	public ArrayList<GameObject> objects = new ArrayList<GameObject>();
	Frame frame;
	
	public static int L= (int) HEIGHT/10;
	public int LengthL;
	double shooterHeight = L;
	double diameter= L/5;
	double puWidth = L/2;
	double speed= 20;
	double shooterX= WIDTH/2;
	double shooterY =HEIGHT - shooterHeight*2;
	double atomX = shooterX+ diameter/2;
	double atomY = shooterY -diameter*2;
	public double rotationConstant = 10;
	double atomSpeed=L/5;
	double shooterMoveConstant = 15;
	private boolean isPaused = false;
	private boolean isLoaded = false;
	public double fallingSpeed = atomSpeed;
	TimerTask timerTask ;
	Timer timer;
	SaveLoadAdapter save;
	Random random = new Random();
	StatisticsWindow statsWindow = StatisticsWindow.getInstance();
	String username="mehmet";
	String DBChoice= "file";
	private static KuVid game;
	
	//Blender
	public Blender blender = new Blender();
	

	public boolean keyB= false;
	public int atomRank;
	public int targetAtomRank;

	public Controller(Renderer UI, Frame frame) {
		this.renderer = UI;
		this.frame = frame;
	}
	
	public void update() {
		/**
		 * @requires an initialized game objects list. The list can be empty but it has to be initialized before.
		 * @modifies the game objects' positions and quantity, also updates time.
		 * @effects for every object which is added to the game objects list, update that object's position in the game,
		 * check whether any collision happens, if so take corresponding action. If the timer hits 0, end the game.
		 */
		statsWindow.getAlphaLabel().setText(Integer.toString(alphaCount));
		statsWindow.getBetaLabel().setText(Integer.toString(betaCount));
		statsWindow.getSigmaLabel().setText(Integer.toString(sigmaCount));
		statsWindow.getGammaLabel().setText(Integer.toString(gammaCount));
		statsWindow.getAlphaPULabel().setText(Integer.toString(alphaPUCount));
		statsWindow.getBetaPULabel().setText(Integer.toString(betaPUCount));
		statsWindow.getSigmaPULabel().setText(Integer.toString(sigmaPUCount));
		statsWindow.getGammaPULabel().setText(Integer.toString(gammaPUCount));
		statsWindow.getHealth().setText(Integer.toString(health));
		//Shields
		statsWindow.getEta().setText(Integer.toString(etaCount));
		statsWindow.getLota().setText(Integer.toString(lotaCount));
		statsWindow.getTheta().setText(Integer.toString(thetaCount));
		statsWindow.getZeta().setText(Integer.toString(zetaCount));
		renderer.setScore(score);
		renderer.setLives(lives);
		statsWindow.getScore().setText(Double.toString(score));
		// Remaining timer
		int timeAsSecond= time;
		String minute=Integer.toString(timeAsSecond/60);
		String second= Integer.toString(timeAsSecond%60);
		if(timeAsSecond%60 ==0) {
			second += "0";
		}else if(timeAsSecond %60 < 10) {
			second = "0"+ second;
		}
		statsWindow.getTime().setText(minute+":"+second);
		if(health<=0 ||(time==0 && !frame.isBuildMode())) {
			System.out.println("Game Over");
			objects= new ArrayList<>();
			renderer.objects= new ArrayList<>();
			renderer.setGameOver(true);
		}
		for (int i = 0; i <= objects.size()-1; i++) {
			GameObject tempobject = (GameObject) objects.get(i);
			renderer.objects.get(i).setX((int) tempobject.getX());
			renderer.objects.get(i).setY((int) tempobject.getY());
			if (i==0) {
				if(tempobject.getType().equals("alpha")|| tempobject.getType().equals("beta")|| tempobject.getType().equals("sigma")||
						tempobject.getType().equals("gamma")) {
					setAtomPositionsAndCheckCollision(tempobject);
				}else {
					setPowerupPositionsAndCheckCollision(tempobject);
				}
				}
			if (i==1) {
				AtomShooter tempobject1= (AtomShooter) tempobject;
				UIShooter shooter = (UIShooter) renderer.objects.get(i);
				int x = (int) tempobject.getX();
				int y = (int) tempobject.getY();
				double rotation = tempobject.getRotationAngle();
				tempobject1.setSpeed(speed);
				shooter.setX(x);
				shooter.setY(y);
				shooter.setRotationAngle(rotation);
				if(!frame.isBuildMode()) {
					shooterCollision(tempobject1);
				}
				}
			if(tempobject.getType()!=null) {
				if (i!=0 && isPowerup(tempobject)) {
					Powerup tempobject1=(Powerup) tempobject;
					if(!frame.isBuildMode()) {
						tempobject1.setSpeed(speed/3);
					}
					tempobject1.fallInStraightLine(tempobject1.getX(), tempobject1.getY());
					if(tempobject1.getY()> HEIGHT) {
						objects.remove(i);
						renderer.objects.remove(i);
					}
				}
				if (i!=0 && isMolecule(tempobject)) {
					if(!frame.isBuildMode()) {
						tempobject.setSpeed(speed/3);
					}
					if(tempobject.getY()> HEIGHT) {
						objects.remove(i);
						renderer.objects.remove(i);
					}
					if(objects.size()==2) {
						System.out.println("Time is Up! Game Over");
						objects= new ArrayList<>();
						renderer.objects= new ArrayList<>();
						renderer.setGameOver(true);
					}
					
					
				}
				if (i!=0 && (tempobject.getType().equals("alpha") ||
						tempobject.getType().equals("beta") || 
						tempobject.getType().equals("sigma") ||
						tempobject.getType().equals("gamma"))) {
					if(!frame.isBuildMode()) {
						tempobject.setSpeed(speed/3);
						blockerRadiusCollision((ReactionBlocker) tempobject,1);
					}
					if(tempobject.getY()> HEIGHT- tempobject.getHeight()*3) {
						double distance= getShooter().getX()-tempobject.getX();
						if(Math.abs(distance) <= tempobject.getWidth()*2) {
							health -= WIDTH/ Math.abs(distance);
						}
						blockerRadiusCollision((ReactionBlocker) tempobject,2);
						objects.remove(i);
						renderer.objects.remove(i);
						
						
					}
					if(objects.size()==2) {
						System.out.println("Time is Up! Game Over");
						objects= new ArrayList<>();
						renderer.objects= new ArrayList<>();
						renderer.setGameOver(true);
					}
					
					
				}
				
			}
		
			tempobject.update();
		}
		
	}
	public Frame getFrame() {
		return frame;
	}
	public void setFrame(Frame frame) {
		this.frame = frame;
	}
	public int getInitialMoleculeCount() {
		return initialMoleculeCount;
	}
	public void setInitialMoleculeCount(int initialMoleculeCount) {
		this.initialMoleculeCount = initialMoleculeCount;
	}
	public void addObject(GameObject obj) {
		this.objects.add(obj);
	}

	public void removeObject(GameObject obj) {
		this.objects.remove(obj);
	}
	
	
	public GameObject getObject(String obj) {
		for(GameObject tempObject: this.objects) {
			if(tempObject.getType()== obj) {
				return tempObject;
			}
		}
		return null;
	}
	public void updateLives() {
		lives--;
	}
	
	public static boolean intersects(Rectangle2D r, Rectangle2D r1) {
		/**
		 * @requires Two rectangle objects with each are specified with valid coordinations, width and height.
		 * @effects returns true if these two rectangles are in a intersection. Such that assume we have 
		 * specified (2,2,4,4) rectangle. It is a rectangle with points starting from 2,2 and containing lines:
		 * (2,6), (6,2) and (6,6). Thus if the other rectangle has any point inside of these lines, then they are intersecting.
		 */
		if(r.intersects(r1) || r1.intersects(r)) {
			return true;
		}
		return false;
	}
	
	private void setAtomPositionsAndCheckCollision(GameObject tempobject) {
		/**
		 * @requires a valid gameObject which strictly has to be an Atom object.
		 * @modifies the atom's position in the frame, atom's existence in the frame.
		 * @effects removes the atom and corresponding molecule from the frame if a collision happens.
		 * 			If a collision does not happen, then it will update the atom's position.
		 */
		Atom tempobject1 = (Atom) tempobject;
		double x = tempobject1.getX();
		double y =  tempobject1.getY();
		if(!tempobject1.isShooted()) {
			double rotation = objects.get(1).getRotationAngle();
			double x2 = -objects.get(1).getHeight()*Math.sin(Math.toRadians(rotation));
			double y2 = objects.get(1).getHeight()*Math.cos(Math.toRadians(rotation));
			x=objects.get(1).getX()-x2+tempobject1.getDiameter()/3;
			y=objects.get(1).getY()-y2+objects.get(1).getHeight()-tempobject1.getHeight()+tempobject1.getDiameter()*2/3;
			tempobject.setX(x);
			tempobject.setY(y);
		}
		else {
			
			tempobject = tempobject1;
			Rectangle2D r= new Rectangle2D.Double(tempobject.getX(),tempobject.getY(),tempobject1.getWidth(),tempobject1.getHeight());
			// Collision with alpha molecule and alpha atom.
			for (int j = 2; j < objects.size(); j++) {
				if(objects.size()==2) {
					break;
				}
				GameObject collisionObject = (GameObject) objects.get(j);
				renderer.objects.get(j).setX((int) collisionObject.getX());
				renderer.objects.get(j).setY((int) collisionObject.getY());
				if ((collisionObject.getType().equals("AlphaMolecule") && tempobject.getType().equals("alpha")) ||
						(collisionObject.getType().equals("BetaMolecule") && tempobject.getType().equals("beta")) ||
						(collisionObject.getType().equals("SigmaMolecule") && tempobject.getType().equals("sigma")) ||
						(collisionObject.getType().equals("GammaMolecule") && tempobject.getType().equals("gamma"))
						) {
					Molecule collisionObject1 = (Molecule) collisionObject;
					UIMolecule molecule = (UIMolecule) renderer.objects.get(j);
					double a = collisionObject1.getX();
					double b = collisionObject1.getY();
					Rectangle2D r1= new Rectangle2D.Double(a,b,collisionObject1.getWidth(),collisionObject1.getHeight());
					if(intersects(r,r1)) {
						System.out.println("Collision");
						objects.remove(collisionObject);
						if(timerTask !=null) {
							timerTask.cancel();
						}
						renderer.removeObject(molecule);
						tempobject.setX(objects.get(1).getX());
						tempobject.setY(objects.get(1).getY());
						tempobject.setRotationAngle(getShooter().getRotationAngle());
						((Atom) tempobject).setShooted(false);
						score += ((Atom)tempobject).getEfficiency();
					}
				}
				}
		}
	}
	
	private void setPowerupPositionsAndCheckCollision(GameObject tempobject) {
		/**
		 * @requires a valid gameObject which strictly has to be an Powerup object.
		 * @modifies the powerup's position in the frame, powerup's existence in the frame.
		 * @effects removes the powerup and corresponding blocker from the frame if a collision happens.
		 * 			If a collision does not happen, then it will update the powerup's position.
		 */
		Powerup tempobject1 = (Powerup) tempobject;
		double x = tempobject1.getX();
		double y =  tempobject1.getY();
		if(!tempobject1.isShooted()) {
			double rotation =objects.get(1).getRotationAngle();
			double x2 = -objects.get(1).getHeight()*Math.sin(Math.toRadians(rotation));
			double y2 = objects.get(1).getHeight()*Math.cos(Math.toRadians(rotation));
			x=objects.get(1).getX()-x2-tempobject1.getWidth()/2;
			y=objects.get(1).getY()-y2-tempobject1.getHeight()*3/2+objects.get(1).getHeight();
			tempobject.setX(x);
			tempobject.setY(y);
			
		}
		else {
			
			tempobject = tempobject1;
			Rectangle2D r= new Rectangle2D.Double(x,y,tempobject1.getWidth(),tempobject1.getHeight());
			for (int j = 2; j < objects.size(); j++) {
				if(objects.size()==0) {
					break;
				}
				GameObject collisionObject = (GameObject) objects.get(j);
				renderer.objects.get(j).setX((int) collisionObject.getX());
				renderer.objects.get(j).setY((int) collisionObject.getY());
				
				//Powerup and Reaction Blocker collision
				if ((collisionObject.getType().equals("alpha") && tempobject.getType()=="+alpha") ||
						(collisionObject.getType().equals("beta") && tempobject.getType()=="+beta") ||
						(collisionObject.getType().equals("sigma") && tempobject.getType()=="+sigma") ||
						(collisionObject.getType().equals("gamma") && tempobject.getType()=="+gamma")
						) {
					ReactionBlocker collisionObject1 = (ReactionBlocker) collisionObject;
					UIReactionBlocker blocker = (UIReactionBlocker) renderer.objects.get(j);
					double a = collisionObject1.getX();
					double b = collisionObject1.getY();
					Rectangle2D r1= new Rectangle2D.Double(a,b,collisionObject1.getWidth(),collisionObject1.getHeight());
					if(intersects(r,r1)) {
						System.out.println("Collision");
						objects.remove(collisionObject);
						if(timerTask !=null) {
							timerTask.cancel();
						}
						renderer.removeObject(blocker);
						tempobject.setX(objects.get(1).getX());
						tempobject.setY(objects.get(1).getY());
						tempobject.setRotationAngle(getShooter().getRotationAngle());
						((Powerup) tempobject).setShooted(false);
					}
					}
			}
		}
	}
	private void blockerRadiusCollision(ReactionBlocker blocker, int radiusConstant) {
		double x= blocker.getX();
		double y= blocker.getY();
		for (int j = 0; j < objects.size(); j++) {
			if(objects.size()==0) {
				break;
			}
			
			GameObject collisionObject = (GameObject) objects.get(j);
			if(j==0 && ((blocker.getType().equals("alpha") && collisionObject.getType().equals("alpha")) ||
					(blocker.getType().equals("beta") && collisionObject.getType().equals("beta")) ||
					(blocker.getType().equals("sigma") && collisionObject.getType().equals("sigma")) ||
					(blocker.getType().equals("gamma") && collisionObject.getType().equals("gamma"))
					)) {
				Rectangle2D r= new Rectangle2D.Double(x,y,blocker.getWidth()*radiusConstant,blocker.getHeight()*radiusConstant);
				Rectangle2D rect= new Rectangle2D.Double(collisionObject.getX(),collisionObject.getY(),collisionObject.getWidth(),collisionObject.getHeight());
				if(intersects(r,rect)) {
					if(timerTask !=null) {
						timerTask.cancel();
					}
					((Atom) collisionObject).setShooted(false);
					
				}
			}
			else if((blocker.getType().equals("alpha") && collisionObject.getType()=="AlphaMolecule") ||
					(blocker.getType().equals("beta") && collisionObject.getType()=="BetaMolecule") ||
					(blocker.getType().equals("sigma") && collisionObject.getType()=="SigmaMolecule") ||
					(blocker.getType().equals("gamma") && collisionObject.getType()=="GammaMolecule")
					)  {
				Rectangle2D r= new Rectangle2D.Double(x,y,blocker.getWidth()*radiusConstant,blocker.getHeight()*radiusConstant);
				Rectangle2D rect= new Rectangle2D.Double(collisionObject.getX(),collisionObject.getY(),collisionObject.getWidth(),collisionObject.getHeight());
				if(intersects(r,rect)) {
					objects.remove(j);
					renderer.objects.remove(j);
				}
			}
		}
	}
	
	public void shootObject(GameObject shootingObject,AtomShooter shooter) {
		/**
		 * @requires a valid gameObject which strictly has to be an Atom or Powerup object, 
		 * a valid AtomShooter object which is initialized before.
		 * @modifies the object's position and the object's isShooted method.
		 * @effects update the object's new position in every timer tick. Update the object's isShooted method
		 * to be true. Start the timerTask for that object which will continue until the object falls down the frame.
		 */
		double shooterRotationAngle=0;
		if(!shootingObject.isShooted() && checkObjectScore(shootingObject.getType())>0) {
			shootingObject.setShooted(true);
			System.out.println("Shoot");
			shooterRotationAngle = shooter.getRotationAngle();
			shootingObject.setRotationAngle(shooterRotationAngle);
			timerTask = new UpdateAtomTask(shootingObject,Toolkit.getDefaultToolkit().getScreenSize(),shooter);
			timer = new Timer(true);
	        timer.scheduleAtFixedRate(timerTask, 0, 40);
	        updateObjectScore(shootingObject.getType());
	        atomX = shooter.getX();
	        shootingObject.setX(atomX);

			
		}
		else {
			System.out.println("The object is already shooted or there is not enough of object:"+ shootingObject.getType());
		}
	}
	
	public void moveShooter(AtomShooter shooter,GameObject shootingObject, String direction) {
		/**
		 * @requires a valid gameObject which strictly has to be an Atom or Powerup object, 
		 * a valid AtomShooter object which is initialized before, and a string that strictly has to be
		 * "left" or "right".
		 * @modifies the shooter's position.
		 * @effects if the signal is left, then move shooter to the left by given units.
		 * 	If the signal is right, then move the shooter to the right by given units.
		 *  Set shooter's position in the frame.
		 */
		if(direction == "left") {
			if (shooter.getX() > 0)
				shooterX -=shooterMoveConstant;
				
			shooter.setX(shooterX);
		}
		else if(direction=="right") {
			System.out.println("Move Shooter right");
			if (shooter.getX() < WIDTH - shooter.getWidth()) {
				shooterX +=shooterMoveConstant;
			}
			shooter.setX(shooterX);
				
		}
	}
	
	public void rotateShooter(AtomShooter shooter, GameObject shootingObject, String direction) {
		/**
		 * @requires a valid gameObject which strictly has to be an Atom or Powerup object, 
		 * a valid AtomShooter object which is initialized before, and a string that strictly has to be
		 * "left" or "right".
		 * @modifies the shooter's angle.
		 * @effects if the signal is left, then rotate shooter to the left by given units.
		 * 	If the signal is right, then rotate the shooter to the right by given units.
		 *  Set shooter's rotation in the frame.
		 */
		double shooterRotationAngle= shooter.getRotationAngle();
		if(direction == "left") {
			System.out.println("Rotate shooter left");
			if(shooterRotationAngle > -90 ) {
				shooterRotationAngle -= rotationConstant;
				shooter.setRotationAngle(shooterRotationAngle);
				shootingObject.setRotationAngle(shooterRotationAngle);
				System.out.println(shootingObject.getRotationAngle());
			}
		}
		else if(direction=="right") {
			System.out.println("Rotate shooter right");
			if(shooterRotationAngle < 90) {
				shooterRotationAngle += rotationConstant;
				shooter.setRotationAngle(shooterRotationAngle);
				shootingObject.setRotationAngle(shooterRotationAngle);
			}
		}
	}
	public void blenderObject(Boolean keyB, int rank) {
		/**
		 * @requires keyB to be true and rank to be 1,2,3 or 4 (KuVid already detects only these numbers in keylistener) 
		 * 			and certain number of the atomRank type of atoms to exists.
		 * @modifies the number of certain types of atoms when they are blended or broken.
		 * @effects if required number of the atomRank type of atoms exists removes and adds corresponding number of atoms,
		 * 			else says "You don't have enough atoms to do this".
		 */
		blender.alphaCount = this.alphaCount;
		blender.betaCount = this.betaCount;
		blender.gammaCount = this.gammaCount;
		blender.sigmaCount = this.sigmaCount;

		this.keyB = keyB;
		if(rank == 0) {
			this.atomRank = 0;
			this.targetAtomRank = 0;
		}
		if(this.atomRank == 0 && this.keyB == true) {
			this.atomRank = rank;
		} else if(this.atomRank != 0 && this.keyB == true) {
			this.targetAtomRank = rank;
			if(this.atomRank < this.targetAtomRank) {
			blender.BlendAtom(this.atomRank, this.targetAtomRank);
			} else {
			blender.BreakAtom(this.atomRank, this.targetAtomRank);
			}
			this.alphaCount = blender.alphaCount;
			this.betaCount = blender.betaCount;
			this.gammaCount = blender.gammaCount;
			this.sigmaCount = blender.sigmaCount;
			
			this.keyB = false;
			this.atomRank = 0;
			this.targetAtomRank = 0;
		}

		System.out.println("BLENDED");
		
	

	}

	public void pauseGame() {
		/**
		 * @effects if there is a timerTask such that an atom or powerup is shooted, stop that task.
		 */
		if(!isPaused) {
			System.out.println("PAUSED");
			if(timerTask!= null) {
				timerTask.cancel();
			}
			isPaused = true;

		}
	}
	public void resumeGame(AtomShooter shooter,GameObject shootingObject) {
		/**
		 * @requires a valid AtomShooter which is initialized. 
		 * A valid gameObject which strictly has to be an Atom or Powerup object.
		 * @effects if the game is paused before, start the game. 
		 * Create a timerTask if the object is shooted before. If the object is not shooted, do not start a timerTask.
		 */
		System.out.println("RESUME");
		if(isPaused) {
			if(getShootingObject().isShooted()) {
				timerTask = new UpdateAtomTask(shootingObject,Toolkit.getDefaultToolkit().getScreenSize(),shooter);
				setTimer(new Timer(true));
				timer.scheduleAtFixedRate(timerTask, 0, 40);
			}
			isPaused= false;
		}
	}
	
	public void setTimer(Timer timer) {
		/**
		 * @requires a valid Timer object.
		 * @modifies the timer object to be set to the given timer object.
		 * @effects given the timer object, set the global timer object to follow given timer object.
		 */
		this.timer = timer;
	}
	
	public void switchAtom() {
		/**
		 * @requires a valid GameObject that is strictly Atom object which must be initialized before.
		 * @modifies the shooting object's type.
		 * @effects change the object's type randomly, if the object is atom and the atom is not shooted or,
		 * If the randomly chosen atom has enough instances to be used.
		 */
		GameObject shootingObject = getShootingObject();
		UIGameObject uiShootingObject = renderer.objects.get(0);
		System.out.println("Switch Atom");
		if(isAtom(shootingObject)) {
			if(!shootingObject.isShooted()) {
				Atom atom = (Atom) shootingObject;
				String temp =atom.getType();
				Atom atom1= AtomFactory.getAtom(atom,"");
				
				if(checkObjectScore(atom1.getType())>0) {
					System.out.println(atom1.getType());
					((UIAtom) uiShootingObject).setAtomType(atom1.getType());
				}
				else {
					atom = AtomFactory.getAtom(atom,temp);
					System.out.println("temp:"+temp);
					System.out.println(shootingObject.getType());
				}
			}
		}
		else {
			if(!shootingObject.isShooted()) {
				shootingObject= new Atom("alpha");
				UIAtom atomui= new UIAtom("alpha");
				((Atom) shootingObject).setDiameter(diameter);
				shootingObject.setX(atomX);
				shootingObject.setY(atomY);
				shootingObject.setSpeed(atomSpeed);
				((Atom) shootingObject).setRotationAngle(getShooter().getRotationAngle());
				atomui.setDiameter(diameter);
				objects.set(0,shootingObject );
				renderer.objects.set(0,atomui);
			}
		}
	}
	
	public static boolean isAtom(GameObject tempobject) {
		/**
		 * @requires a valid GameObject.
		 * @effects returns true if the specified gameobject is one of the instances of atom object.
		 */
		if(tempobject.getType().equals("alpha")|| tempobject.getType().equals("beta")|| tempobject.getType().equals("sigma")||
				tempobject.getType().equals("gamma")) {
			return true;
		}
		return false;
	}
	public static boolean isPowerup(GameObject tempobject) {
		/**
		 * @requires a valid GameObject.
		 * @effects returns true if the specified gameobject is one of the instances of powerup object.
		 */
		if(tempobject.getType().equals("+alpha")|| tempobject.getType().equals("+beta")|| tempobject.getType().equals("+sigma")||
				tempobject.getType().equals("+gamma")) {
			return true;
		}
		return false;
	}
	public static boolean isMolecule(GameObject tempobject) {
		/**
		 * @requires a valid GameObject.
		 * @effects returns true if the specified gameobject is one of the instances of powerup object.
		 */
		if(tempobject.getType().equals("AlphaMolecule")|| tempobject.getType().equals("BetaMolecule")|| tempobject.getType().equals("SigmaMolecule")||
				tempobject.getType().equals("GammaMolecule")) {
			return true;
		}
		return false;
	}
	public void saveGame() {
		/**
		 * @requires a valid GameObject that is strictly Atom or Powerup object which must be initialized before.
		 * @modifies the JSON file that is used as the saver/loader file. Uses an SaveLoadAdapter class to adapt changes.
		 * @effects save the game into a specified file or database (whichever is given in the adapter) using some key instances
		 * such as username, the gameobject counts, types and positions etc.
		 */
		
//		GameObject shootingObject= getShootingObject();
		if(getDBChoice().equals("file")) {
			save= new SaveLoadAdapter(new SaveFile(this));
		}
		else if(getDBChoice().equals("database")) {
			save= new SaveLoadAdapter(new SaveDatabase(this));
		}
		save.saveGame();
	}
	public void loadGame() {
		/**
		 * @modifies the controller's objects list and every each game instances.
		 * @effects load the game from the specified Adapter and load interface classes.
		 * If the specified object is shooted when saved, start the timerTask for that object.
		 */
		getDatabaseChoice();
		if(!isLoaded) {
			isLoaded= true;
			if(save==null) {
				if(getDBChoice().equals("file")) {
					save = new SaveLoadAdapter(new SaveFile(this));
				}
				else if(getDBChoice().equals("database")) {
					save= new SaveLoadAdapter(new SaveDatabase(this));
				}
				System.out.println("new save");
			}
			save.loadGame();
			if(getShootingObject().isShooted()) {
				TimerTask timerTask = new UpdateAtomTask(getShootingObject(),Toolkit.getDefaultToolkit().getScreenSize(),(AtomShooter) getShooter());
				setTimer(new Timer(true));
				timer.scheduleAtFixedRate(timerTask, 0, 40);
				setPaused(false);
			}
		}
		
	}
	public GameObject getShootingObject() {
		return this.objects.get(0);
	}
	public GameObject setShootingObject(GameObject obj) {
		return this.objects.set(0,obj);
	}
	public UIGameObject getUIShootingObject() {
		return this.renderer.objects.get(0);
	}
	public Renderer getRenderer() {
		return this.renderer;
	}
	public static KuVid getGame() {
		return game;
	}

	public void addObject(int i, GameObject obj) {
		/**
		 * @requires a valid GameObject of any instance. A valid position in the list
		 * @modifies the controller's objects list.
		 * @effects update the objects list with given object.
		 */
			this.objects.add(i,obj);
		
	}
	public GameObject getShooter() {
		/**
		 * @effects returns the object list's element at the first index which is always 
		 * specified as shooter object in this project's structure.
		 */
		return this.objects.get(1);
	}
	
	private void updateObjectScore(String type) {
		/**
		 * @requires a valid string which indicates corresponding number of atoms, powerups or shields.
		 * @modifies the number of specified type of gameObject.
		 * @effects decrease the quantity of specified object. i.e a shield object is used, decrease corresponding
		 * shield by one. Or when an atom or powerup is shooted, decrease that object's quantity.
		 */
		if(type.equals("alpha")) {
			alphaCount--;
			System.out.println(alphaCount);
		}
		else if(type.equals("beta")) {
			betaCount--;
		}
		else if(type.equals("sigma")) {
			sigmaCount--;
		}
		else if(type.equals("gamma")) {
			gammaCount--;
		}
		else if(type.equals("+alpha")) {
			alphaPUCount--;
		}
		else if(type.equals("+beta")) {
			betaPUCount--;
		}
		else if(type.equals("+sigma")) {
			sigmaPUCount--;
		}
		else if(type.equals("eta")) {
			etaCount--;
		}
		else if(type.equals("lota")) {
			lotaCount--;
		}
		else if(type.equals("theta")) {
			thetaCount--;
		}
		else if(type.equals("zeta")) {
			zetaCount--;
		}
		else {
			gammaPUCount--;
		}
	}
	private void increasePUScore(String type) {
		/**
		 * @requires a valid string which indicates corresponding number of powerups.
		 * @modifies the number of specified type of gameObject.
		 * @effects increase the quantity of specified object. i.e a powerup is catched, then increase the total number of
		 * corresponding powerups.
		 */
		if(type.equals("+alpha")) {
			alphaPUCount++;
		}
		else if(type.equals("+beta")) {
			betaPUCount++;
		}
		else if(type.equals("+sigma")) {
			sigmaPUCount++;
		}
		else {
			gammaPUCount++;
		}
	}
	private int checkObjectScore(String type) {
		/**
		 * @requires a valid string which indicates corresponding number of atoms, powerups or shields.
		 * @effects return the specified object's quantity in the game. i.e return the number of atoms, powerups or
		 * shields.
		 */
		if(type.equals("alpha")) {
			return alphaCount;
		}
		else if(type.equals("beta")) {
			return betaCount;
		}
		else if(type.equals("sigma")) {
			return sigmaCount;
		}
		else if(type.equals("gamma")) {
			return gammaCount;
		}
		else if(type.equals("+alpha")) {
			return alphaPUCount;
		}
		else if(type.equals("+beta")) {
			return betaPUCount;
		}
		else if(type.equals("+sigma")) {
			return sigmaPUCount;
		}
		else if(type.equals("eta")) {
			return etaCount;
		}
		else if(type.equals("lota")) {
			return lotaCount;
		}
		else if(type.equals("theta")) {
			return thetaCount;
		}
		else if(type.equals("zeta")) {
			return zetaCount;
		}
		else {
			return gammaPUCount;
		}
	}
	private void shooterCollision(AtomShooter shooter) {
		/**
		 * @requires a valid AtomShooter which has to be initialized before.
		 * @modifies the number of powerup available to the player.
		 * @effects if the AtomShooter catches a powerup, increase the quantity of powerups and remove the powerup object from
		 * the frame. 
		 */
		int x = (int) shooter.getX();
		int y = (int) shooter.getY();
		Rectangle2D r= new Rectangle2D.Double(x,y,shooter.getWidth(),shooter.getHeight());
		// Collision with alpha molecule and alpha atom.
		for (int j = 2; j < objects.size(); j++) {
			if(objects.size()==0) {
				break;
			}
			
			
			GameObject collisionObject = (GameObject) objects.get(j);
			if(collisionObject.getType()!=null) {
				if (collisionObject.getType().equals("+alpha") || collisionObject.getType().equals("+beta") ||
						collisionObject.getType().equals("+sigma") || collisionObject.getType().equals("+gamma")) {
					Powerup collisionObject1 = (Powerup) collisionObject;
					UIPowerup pu = (UIPowerup) renderer.objects.get(j);
					double a = collisionObject1.getX();
					double b = collisionObject1.getY();
					Rectangle2D r1= new Rectangle2D.Double(a,b,collisionObject1.getHeight(),collisionObject1.getWidth());
					if(intersects(r,r1)) {
						System.out.println("Collision");
						objects.remove(collisionObject);
						renderer.removeObject(pu);
						increasePUScore(collisionObject.getType());
						
					}
				}
			}
		}
	}

	public void setAlphaCount(int alphaCount) {
		this.alphaCount = alphaCount;
	}

	public void setBetaCount(int betaCount) {
		this.betaCount = betaCount;
	}

	public void setSigmaCount(int sigmaCount) {
		this.sigmaCount = sigmaCount;
	}

	public void setGammaCount(int gammaCount) {
		this.gammaCount = gammaCount;
	}

	public void setBetaPUCount(int betaPUCount) {
		this.betaPUCount = betaPUCount;
	}

	public int getAlphaCount() {
		return alphaCount;
	}

	public int getBetaCount() {
		return betaCount;
	}

	public int getSigmaCount() {
		return sigmaCount;
	}

	public int getGammaCount() {
		return gammaCount;
	}

	public int getAlphaPUCount() {
		return alphaPUCount;
	}

	public void setAlphaPUCount(int alphaPUCount) {
		this.alphaPUCount = alphaPUCount;
	}

	public int getSigmaPUCount() {
		return sigmaPUCount;
	}

	public void setSigmaPUCount(int sigmaPUCount) {
		this.sigmaPUCount = sigmaPUCount;
	}

	public int getGammaPUCount() {
		return gammaPUCount;
	}

	public void setGammaPUCount(int gammaPUCount) {
		this.gammaPUCount = gammaPUCount;
	}

	public int getBetaPUCount() {
		return betaPUCount;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void shieldClicked(String type) {
		/**
		 * @requires a valid string which strictly indicates a shield object. 
		 * @modifies the number of specified type of shield and current shooting object's (which has to strictly be an atom)
		 * efficiency by using decorator pattern, which changes the object's one value (which is efficiency in this case)
		 * but does not change or interfere the object's main functions.
		 * @effects decrease the quantity of specified shield. Set the current shooting object's (which has to strictly be an atom)
		 * efficiency to be shootingObject's efficiency + specified shield's efficiency. Also decrease the speed by corresponding shield
		 * values.
		 */
	if(isAtom(getShootingObject()) && checkObjectScore(type)>0 && !getShootingObject().isShooted()) {
		Atom shootingObject= (Atom) getShootingObject();
		if(type.equals("eta")) {
			setShootingObject(new Eta(shootingObject));
		}
		else if(type.equals("lota")) {
			setShootingObject(new Lota(shootingObject));
			
		}
		else if(type.equals("theta")) {
			setShootingObject(new Theta(shootingObject));
		}
		else {
			setShootingObject(new Zeta(shootingObject));
		}
		updateObjectScore(type);
	}
	System.out.println("efficiency:"+((Atom)getShootingObject()).getEfficiency());
	System.out.println("speed:"+((Atom)getShootingObject()).getSpeed());
	}

	public int getEtaCount() {
		return etaCount;
	}

	public void setEtaCount(int etaCount) {
		this.etaCount = etaCount;
	}

	public int getLotaCount() {
		return lotaCount;
	}

	public void setLotaCount(int lotaCount) {
		this.lotaCount = lotaCount;
	}

	public int getThetaCount() {
		return thetaCount;
	}

	public void setThetaCount(int thetaCount) {
		this.thetaCount = thetaCount;
	}

	public int getZetaCount() {
		return zetaCount;
	}

	public void setZetaCount(int zetaCount) {
		this.zetaCount = zetaCount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public void switchToPowerup(String puType) {
		/**
		 * @requires a valid string which strictly indicates a powerup object. 
		 * @modifies the first element of the objects list which indicates the shooting object.
		 * @effects update the shooting object to be a powerup regarding the specified powerup type.
		 */
		if(!getShootingObject().isShooted()) {
			System.out.println("Pu Alpha clicked");
			Powerup pu= new Powerup(puType);
			pu.setHeight(diameter*2);
			pu.setWidth(diameter*2);
			pu.setSpeed(atomSpeed);
			pu.setRotationAngle(getShooter().getRotationAngle());
			objects.set(0,pu );
			UIPowerup puUI= new UIPowerup(puType);
			puUI.setHeight(diameter*4);
			puUI.setWidth(diameter*4);
			renderer.objects.set(0,puUI );
		}
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	public boolean isKeyB() {
		return keyB;
	}

	public void setKeyB(boolean keyB) {
		this.keyB = keyB;
	}

	public int getAtomRank() {
		return atomRank;
	}

	public void setAtomRank(int atomRank) {
		this.atomRank = atomRank;
	}

	public int getTargetAtomRank() {
		return targetAtomRank;
	}

	public void setTargetAtomRank(int targetAtomRank) {
		this.targetAtomRank = targetAtomRank;
	}

	public int getLengthL() {
		return LengthL;
	}

	public void setLengthL(int lenghtL) {
		LengthL = lenghtL;
	}

	public double getDiameter() {
		return diameter;
	}

	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}

	public double getShooterX() {
		return shooterX;
	}

	public void setShooterX(double shooterX) {
		this.shooterX = shooterX;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getDBChoice() {
		return DBChoice;
	}

	public void setDBChoice(String dBChoice) {
		DBChoice = dBChoice;
	}
	public void getDatabaseChoice() {
		if(frame==null) {
			this.setDBChoice("file");
		}
		else if(frame.getDBGroup().getSelection().getActionCommand().equals("fileDB")) {
			System.out.println("The game will be saved into file");
			this.setDBChoice("file");
		}
		else if(frame.getDBGroup().getSelection().getActionCommand().equals("mongoDB")) {
			System.out.println("The game will be saved into mongo database");
			this.setDBChoice("database");
		}
		
		
	}
	
}
