package ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import domain.Controller;
import domain.GameObject;
import domain.atom.Atom;
import domain.blender.Blender;
import domain.shooter.AtomShooter;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * @author MehmetUstek
 *
 */
public class KuVid extends Canvas implements Runnable {
	/**
	 * OVERVIEW: This class is a UI class where the player plays, loads, saves and controls the game objects.
	 */
	private static final long serialVersionUID = 1L;
	private boolean running = false;
	public static double WIDTH =  Toolkit.getDefaultToolkit().getScreenSize().getWidth()-180,
			HEIGHT =  Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private Thread thread;
	Controller controller;
	Renderer renderer;
	private Frame window = new Frame(Toolkit.getDefaultToolkit().getScreenSize(), "KuVid", this);
	private static KuVid game;
	public String username;
	public double L= HEIGHT/10;
	double shooterHeight = L;
	double diameter= L/10;
	double speed= L;
	double shooterX;
	double shooterY;
	double atomX;
	double atomY;
	private boolean isPaused = false;
	
//	private boolean atomShooted=false;
	Random random = new Random();
	Timer timer;
	double shooterRotationAngle=0;
	double rotationConstant = 10;
	double atomSpeed=L/5;
	double shooterMoveConstant = 15;
	//These instances are only for the KUVID Game class atom. When these instances is used in this class they will be called with their get methods.
	// The atom class will be added to the collisions no matter what.
	// Initial shooting object.
	Atom shootingObject = new Atom("alpha");
	UIAtom atomui = new UIAtom(shootingObject.getType());
	
	//Shooter
	AtomShooter shooter = new AtomShooter("shooter");
	UIShooter shooterui = new UIShooter("shooter");
	
	//Blender
	Blender blender = new Blender();
	UIBlender blenderui = new UIBlender(150, 150);
	boolean keyB;
	int atomRank;
	int targetAtomRank;
	public static ArrayList<Atom> alphaList= new ArrayList<Atom>();
	public static ArrayList<Atom> betaList= new ArrayList<Atom>();
	public static ArrayList<Atom> sigmaList= new ArrayList<Atom>();
	public static ArrayList<Atom> gammaList= new ArrayList<Atom>();

	public KuVid() {
		/**
		 * @modifies the ui objects' visibility and positions in the frame. Evaluates the player's actions and
		 * sends them to the controller's corresponding method.
		 * @effects display every element which is added to the UI, using Renderer's render method.
		 * Take keyboard and mouse actions and send them to the controller class, where evaluation is handled.
		 */
		
			renderer =new Renderer();
			controller = new Controller(renderer, window);
			this.requestFocus();
			controller.setTime(600);
			
			// Atom settings.
			shootingObject.setDiameter(20);
			
			shootingObject.setSpeed(atomSpeed);
			shootingObject.setRotationAngle(shooterRotationAngle);
			atomui.setDiameter(20);
			
			renderer.addObject(atomui);
			controller.addObject(shootingObject);
			
			
			// Shooter Settings.
			shooter.setWidth(shooterHeight *7/16);
			shooter.setHeight(shooterHeight*7/4);
			shooterX= WIDTH/2;
			shooterY =HEIGHT - shooter.getHeight()*11.5/10;
			shooter.setX(shooterX);
			shooter.setY(shooterY);
			shooter.setRotationAngle(shooterRotationAngle);
			shooterui.setWidth(shooterHeight *7/16 );
			shooterui.setHeight(shooterHeight*7/4);
			
			atomX = shooterX + diameter/2;
			atomY = shooterY/2 - diameter*2;
			shootingObject.setX(atomX);
			shootingObject.setY(atomY);
			renderer.addObject(shooterui);
			controller.addObject(shooter);
			
			
			this.addKeyListener(new KeyListener() {
				public void keyPressed(KeyEvent e) {
					

					switch (e.getKeyCode()) {
					case KeyEvent.VK_UP:
						controller.shootObject(getShootingObject(), shooter);

						break;
					case  KeyEvent.VK_L:
						System.out.println("LOADED");
						controller.loadGame();
						resumeGame();
						
						break;
					case  KeyEvent.VK_S:
						System.out.println("SAVED");
						pauseGame();
						controller.saveGame();
						
						break;
					case  KeyEvent.VK_LEFT:
						controller.moveShooter(shooter, shootingObject, "left");
						break;
					case  KeyEvent.VK_RIGHT:
						controller.moveShooter(shooter, shootingObject, "right");
						break;
					case  KeyEvent.VK_A:
						controller.rotateShooter(shooter, shootingObject, "left");
						
						break;
					case  KeyEvent.VK_D:
						controller.rotateShooter(shooter, shootingObject, "right");
						break;
					case  KeyEvent.VK_P:
						pauseGame();
						break;
					case  KeyEvent.VK_R:
						System.out.println("RESUME");
						resumeGame();
						
						break;
					case  KeyEvent.VK_C:
						controller.switchAtom();
						
						break;
					case  KeyEvent.VK_B:
						System.out.println("BLEND");
						controller.blenderObject(true, 0);
						break;
					case  KeyEvent.VK_1:
						controller.blenderObject(controller.keyB, 1);
						if(controller.keyB==false) {
							JOptionPane optionPane = new JOptionPane("Successful!",JOptionPane.INFORMATION_MESSAGE);
							JDialog dialog = optionPane.createDialog("Blender");
							dialog.setModal(false);
							dialog.setVisible(true); // to visible the dialog
							new Timer(1000, new ActionListener() {
			                    @Override
			                    public void actionPerformed(ActionEvent e) {
			                        dialog.setVisible(false);
			                    }
			                }).start();
						}
						break;
					case  KeyEvent.VK_2:
						controller.blenderObject(controller.keyB, 2);
						if(controller.keyB==false) {
							JOptionPane optionPane = new JOptionPane("Successful!",JOptionPane.INFORMATION_MESSAGE);
							JDialog dialog = optionPane.createDialog("Blender");
							dialog.setModal(false);
							dialog.setVisible(true); // to visible the dialog
							new Timer(1000, new ActionListener() {
			                    @Override
			                    public void actionPerformed(ActionEvent e) {
			                        dialog.setVisible(false);
			                    }
			                }).start();
						}
						break;
					case  KeyEvent.VK_3:
						
						controller.blenderObject(controller.keyB, 3);
						if(controller.keyB==false) {
							JOptionPane optionPane = new JOptionPane("Successful!",JOptionPane.INFORMATION_MESSAGE);
							JDialog dialog = optionPane.createDialog("Blender");
							dialog.setModal(false);
							dialog.setVisible(true); // to visible the dialog
							new Timer(1000, new ActionListener() {
			                    @Override
			                    public void actionPerformed(ActionEvent e) {
			                        dialog.setVisible(false);
			                    }
			                }).start();
						}
						
						break;
					case  KeyEvent.VK_4:
						controller.blenderObject(controller.keyB, 4);
						if(controller.keyB==false) {
							JOptionPane optionPane = new JOptionPane("Successful!",JOptionPane.INFORMATION_MESSAGE);
							JDialog dialog = optionPane.createDialog("Blender");
							dialog.setModal(false);
							dialog.setVisible(true); // to visible the dialog
							new Timer(1000, new ActionListener() {
			                    @Override
			                    public void actionPerformed(ActionEvent e) {
			                        dialog.setVisible(false);
			                    }
			                }).start();
						}
						break;

						
					default:
						break;
					}
				}

				@Override
				public void keyReleased(KeyEvent arg0) {
					
				}

				@Override
				public void keyTyped(KeyEvent arg0) {
					
				}
				
			});
			
			window.getLogin().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					username= window.getUsername().getText();
					controller.setUsername(username);
					showMessageDialog(null, "Logged in succesfully! Now you can load the game with L key!");
				}
				
			});
			//SHIELDS
			window.getStatsWindow().getEtaLabel().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					controller.shieldClicked("eta");
					
				}
				
			});
			window.getStatsWindow().getLotaLabel().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					controller.shieldClicked("lota");
				}
				
			});
			window.getStatsWindow().getThetaLabel().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					controller.shieldClicked("theta");
				}
				
			});
			window.getStatsWindow().getZetaLabel().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					controller.shieldClicked("zeta");
				}
				
			});
			
			//POWERUPS
			window.getStatsWindow().getPuAlpha().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					controller.switchToPowerup("+alpha");
				}
				
			});
			window.getStatsWindow().getPuBeta().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					controller.switchToPowerup("+beta");
				}
				
			});
			window.getStatsWindow().getPuSigma().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					controller.switchToPowerup("+sigma");
				}
				
			});
			window.getStatsWindow().getPuGamma().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					controller.switchToPowerup("+gamma");
				}
				
			});
			

				
			window.getQuitButton().addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				@Override
				public void actionPerformed(ActionEvent e) {
					window.dispose();
					thread.stop();
					running=false;
					new Main();
				}
			});
			
		start();
	}
	

	public synchronized void start() {
		/**
		 * @modifies the thread object.
		 * @effects initialize and start the thread
		 */
		System.out.println("Started");
		thread = new Thread(this);
		running = true;
		thread.start();
		
	}
	@Override
	public void run() {
		/**
		 * @effects run the thread every second.
		 */
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				delta--;
			}
			if (running)
				render();
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				if(controller.getTime()==0) {
					stop();
				}
				else {
					controller.setTime(controller.getTime()-1);
				}
				
			}
		}
		stop();
	}
	
	private void render() {
		/**
		 * @modifies the ui objects's visibility in the frame
		 * @effects display every element which is added to the UI, using Renderer's render method.
		 */
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, (int)WIDTH, (int) HEIGHT);
		renderer.render(g);
		g.dispose();
		bs.show();
	}


	public void update() {
		/**
		 * @effects update the UI objects' specifics after every specified time period.
		 */
		controller.update();
	}
	
	public synchronized void stop() {
		/**
		 * @modifies the thread object.
		 * @effects stop the current thread object.
		 */
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static KuVid getGame() {
		return game;
	}
	public static void setGame(KuVid game) {
		KuVid.game = game;
	}
//	public static void main(String[] args) {
//		game = new KuVid();
//	}
	
	public GameObject getShootingObject() {
		return controller.objects.get(0);
	}
	public UIGameObject getUIShootingObject() {
		return renderer.objects.get(0);
	}
	

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	public boolean isAtom(GameObject tempobject) {
		if(tempobject.getType()=="alpha"|| tempobject.getType()=="beta"|| tempobject.getType()=="sigma"||
				tempobject.getType()=="gamma") {
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	private void pauseGame() {
		/**
		 * @modifies the game's isPaused method, and current thread in the game.
		 * @effects call the controller's pause method and stop the thread.
		 */
		if(!isPaused) {
			System.out.println("PAUSED");
			thread.stop();
//			timerTask.cancel();
			isPaused = true;
		}
		controller.pauseGame();
	}
	private void resumeGame() {
		/**
		 * @modifies the game's isPaused method, and current thread in the game.
		 * @effects call the controller's resume method and re-start the thread.
		 */
		if(isPaused) {
			start();
			controller.resumeGame(shooter, shootingObject);
			isPaused= false;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
