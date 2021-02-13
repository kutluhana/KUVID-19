package ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * @author MehmetUstek
 *
 */
public class Frame extends Canvas {

	private static final long serialVersionUID = 1L;
	public static int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
			HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private JPanel sidebar = new JPanel();
	private JButton quitButton = new JButton("Quit");
	private JTextField alphaMoleculeType = new JTextField("AlphaMolecule");
	private JTextField alphaMoleculeCount = new JTextField(10);
	private JTextField betaMoleculeType = new JTextField("BetaMolecule");
	private JTextField sigmaMoleculeType = new JTextField("SigmaMolecule");
	private JTextField gammaMoleculeType = new JTextField("GammaMolecule");
	
	private JTextField betaMoleculeCount = new JTextField(10);
	private JTextField sigmaMoleculeCount = new JTextField(10);
	private JTextField gammaMoleculeCount = new JTextField(10);
	
	private JTextField apuText = new JTextField("Alpha Pu");
	private JTextField bpuText = new JTextField("Beta PU");
	private JTextField spuText = new JTextField("Sigma PU");
	private JTextField gpuText = new JTextField("Gamma PU");
	
	private JTextField alphaPuCount = new JTextField(10);
	private JTextField betaPuCount = new JTextField(10);
	private JTextField gammaPuCount = new JTextField(10);
	private JTextField sigmaPuCount = new JTextField(10);
	
	private JTextField alphaAtomType = new JTextField("AlphaAtom");
	private JTextField betaAtomType = new JTextField("BetaAtom");
	private JTextField sigmaAtomType = new JTextField("SigmaAtom");
	private JTextField gammaAtomType = new JTextField("GammaAtom");
	
	private JTextField alphaAtomCount = new JTextField(10);
	private JTextField betaAtomCount = new JTextField(10);
	private JTextField sigmaAtomCount = new JTextField(10);
	private JTextField gammaAtomCount = new JTextField(10);
	
	//Shields
	private JTextField etaType = new JTextField("Eta");
	private JTextField lotaType = new JTextField("Lota");
	private JTextField thetaType = new JTextField("Theta");
	private JTextField zetaType = new JTextField("Zeta");
	private JTextField etaCount = new JTextField(10);
	private JTextField lotaCount = new JTextField(10);
	private JTextField thetaCount = new JTextField(10);
	private JTextField zetaCount = new JTextField(10);
	
	//Blocker
	private JTextField ablockerField = new JTextField("Alpha Blocker");
	private JTextField ablockerCount = new JTextField(10);
	private JTextField bblockerField = new JTextField("Beta Blocker");
	private JTextField bblockerCount = new JTextField(10);
	private JTextField sblockerField = new JTextField("Sigma Blocker");
	private JTextField sblockerCount = new JTextField(10);
	private JTextField gblockerField = new JTextField("Gamma Blocker");
	private JTextField gblockerCount = new JTextField(10);

	private JFrame frame;
	private JPanel gui = new JPanel(new GridLayout(0,1));
	private JPanel simpleGui = new JPanel(new FlowLayout());  
	private JPanel gamePanel = new JPanel(new FlowLayout());
	private JButton Game = new JButton ("Play Game");
	private JButton buildMode = new JButton("Build Mode");
	StatisticsWindow statsWindow;
	private JButton applyButton = new JButton("Apply");
	private boolean isBuildMode= false;
	JTextField username= new JTextField("Enter username:");
	JButton login = new JButton("login");
	// Time
	JLabel time= new JLabel("Time(As Seconds)");
	JTextField gameTime = new JTextField("600");
	
	//L Length
	JLabel lengthofAtom= new JLabel("L(lenght of atom)");
	JTextField lengthL = new JTextField("200");
	
	
	
	//Hardness of the game
	private JPanel hardnessGui = new JPanel(new FlowLayout());  
	JLabel hardness= new JLabel("Difficulty");
	JRadioButton easyBox= new JRadioButton("Easy");
	JRadioButton mediumBox= new JRadioButton("Medium");
	JRadioButton hardBox= new JRadioButton("Hard");
	ButtonGroup bGroup = new ButtonGroup();
	
	private JPanel databaseChoice= new JPanel(new FlowLayout());
	JLabel database= new JLabel("Database:");
	JRadioButton fileDB= new JRadioButton("File");
	JRadioButton mongoDB= new JRadioButton("Database");
	ButtonGroup DBGroup = new ButtonGroup();
	
	public Frame(Dimension d, String title, KuVid game) {
		frame = new JFrame(title);
		isBuildMode= false;
		frame.setMaximumSize(d);
		frame.setMinimumSize(new Dimension(1, 1));
		frame.setSize(frame.getMaximumSize());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setBackground(Color.WHITE);
		frame.setLocationRelativeTo(null);
		sidebar.add(quitButton);
		sidebar.add(username);
		sidebar.add(login);
		sidebar.setBackground(Color.white);
		sidebar.setBounds(10, 10, 300, 40);
		statsWindow = StatisticsWindow.getInstance();
		
		frame.add(sidebar);
		frame.add(statsWindow);
		
		//Database Choice
		fileDB.setActionCommand("fileDB");
		mongoDB.setActionCommand("mongoDB");
		databaseChoice.add(database);
		databaseChoice.add(fileDB);
		databaseChoice.add(mongoDB);
		DBGroup.add(fileDB);
		DBGroup.add(mongoDB);
		DBGroup.setSelected(fileDB.getModel(), true);
		databaseChoice.setLayout(new GridLayout(0,3));
		databaseChoice.setBounds(10, 60, 300, 20);
		frame.add(databaseChoice);
		
		frame.add(game);
		statsWindow.setBounds((int) KuVid.WIDTH+10,10,150, (int) KuVid.HEIGHT*9/10);
		statsWindow.setLayout(new GridLayout(0,2));
		frame.setVisible(true);
	}
	
	
	public Frame(Dimension d, String title, BuildMode buildmode) {
		
		frame = new JFrame(title);
		isBuildMode= true;
		frame.setMaximumSize(d);
		frame.setMinimumSize(new Dimension(1, 1));
		frame.setSize(frame.getMaximumSize());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		sidebar.add(quitButton);
		sidebar.add(applyButton);
		sidebar.setBackground(Color.white);
		sidebar.setBounds(10, 10, 200, 40);
		
		frame.add(sidebar);
		alphaMoleculeType.setEditable(false);
		betaMoleculeType.setEditable(false);
		sigmaMoleculeType.setEditable(false);
		gammaMoleculeType.setEditable(false);
		alphaAtomType.setEditable(false);
		betaAtomType.setEditable(false);
		sigmaAtomType.setEditable(false);
		gammaAtomType.setEditable(false);
		apuText.setEditable(false);
		bpuText.setEditable(false);
		spuText.setEditable(false);
		gpuText.setEditable(false);
		etaType.setEditable(false);
		lotaType.setEditable(false);
		thetaType.setEditable(false);
		zetaType.setEditable(false);
		ablockerField.setEditable(false);
		bblockerField.setEditable(false);
		sblockerField.setEditable(false);
		gblockerField.setEditable(false);
		
		alphaAtomCount.setEditable(true);
		alphaAtomCount.setText("20");
		betaAtomCount.setEditable(true);
		betaAtomCount.setText("20");
		sigmaAtomCount.setEditable(true);
		sigmaAtomCount.setText("20");
		gammaAtomCount.setEditable(true);
		gammaAtomCount.setText("20");
		alphaMoleculeCount.setEditable(true);
		alphaMoleculeCount.setText("5");
		betaMoleculeCount.setEditable(true);
		betaMoleculeCount.setText("5");
		sigmaMoleculeCount.setEditable(true);
		sigmaMoleculeCount.setText("5");
		gammaMoleculeCount.setEditable(true);
		gammaMoleculeCount.setText("5");
		
		//Shields
		etaCount.setEditable(true);
		etaCount.setText("20");
		lotaCount.setEditable(true);
		lotaCount.setText("20");
		thetaCount.setEditable(true);
		thetaCount.setText("20");
		zetaCount.setEditable(true);
		zetaCount.setText("20");
		
		alphaPuCount.setEditable(true);
		alphaPuCount.setText("5");
		betaPuCount.setEditable(true);
		betaPuCount.setText("5");
		sigmaPuCount.setEditable(true);
		sigmaPuCount.setText("5");
		gammaPuCount.setEditable(true);
		gammaPuCount.setText("5");
		
		ablockerCount.setEditable(true);
		ablockerCount.setText("5");
		bblockerCount.setEditable(true);
		bblockerCount.setText("5");
		sblockerCount.setEditable(true);
		sblockerCount.setText("5");
		gblockerCount.setEditable(true);
		gblockerCount.setText("5");
		
		gameTime.setEditable(true);
		simpleGui.add(new JLabel("Username"));
		simpleGui.add(username);
		simpleGui.add(lengthofAtom);
		simpleGui.add(lengthL);
		simpleGui.add(time);
		simpleGui.add(gameTime);
		simpleGui.add( alphaAtomType );
		simpleGui.add( alphaAtomCount );
		simpleGui.add( betaAtomType );
		simpleGui.add( betaAtomCount );
		simpleGui.add( sigmaAtomType );
		simpleGui.add( sigmaAtomCount );
		simpleGui.add( gammaAtomType );
		simpleGui.add( gammaAtomCount );
		
		simpleGui.add( alphaMoleculeType );
		simpleGui.add( alphaMoleculeCount );
		simpleGui.add( betaMoleculeType );
		simpleGui.add( betaMoleculeCount );
		simpleGui.add( sigmaMoleculeType );
		simpleGui.add( sigmaMoleculeCount );
		simpleGui.add( gammaMoleculeType );
		simpleGui.add( gammaMoleculeCount );
		simpleGui.add( apuText);
		simpleGui.add( alphaPuCount );
		simpleGui.add( bpuText);
		simpleGui.add( betaPuCount );
		simpleGui.add( spuText);
		simpleGui.add( sigmaPuCount );
		simpleGui.add( gpuText);
		simpleGui.add( gammaPuCount );
		simpleGui.add(etaType);
		simpleGui.add(etaCount);
		simpleGui.add(lotaType);
		simpleGui.add(lotaCount);
		simpleGui.add(thetaType);
		simpleGui.add(thetaCount);
		simpleGui.add(zetaType);
		simpleGui.add(zetaCount);
		simpleGui.add(ablockerField);
		simpleGui.add(ablockerCount);
		simpleGui.add(bblockerField);
		simpleGui.add(bblockerCount);
		simpleGui.add(sblockerField);
		simpleGui.add(sblockerCount);
		simpleGui.add(gblockerField);
		simpleGui.add(gblockerCount);
		simpleGui.setLayout(new GridLayout(0,2));
		
		gui.add(simpleGui);
		
		//Hardness
		easyBox.setActionCommand("easy");
		mediumBox.setActionCommand("medium");
		hardBox.setActionCommand("hard");
		hardnessGui.add(hardness);
		hardnessGui.add(easyBox);
		hardnessGui.add(mediumBox);
		hardnessGui.add(hardBox);
		bGroup.add(easyBox);
		bGroup.add(mediumBox);
		bGroup.add(hardBox);
		bGroup.setSelected(mediumBox.getModel(), true);
		hardnessGui.setLayout(new GridLayout(0,1));
		hardnessGui.setBounds(WIDTH*2/10, HEIGHT/2 , 100, 100);
		frame.add(hardnessGui);
		
		
		//Database Choice
		fileDB.setActionCommand("fileDB");
		mongoDB.setActionCommand("mongoDB");
		databaseChoice.add(database);
		databaseChoice.add(fileDB);
		databaseChoice.add(mongoDB);
		DBGroup.add(fileDB);
		DBGroup.add(mongoDB);
		DBGroup.setSelected(fileDB.getModel(), true);
		databaseChoice.setLayout(new GridLayout(0,1));
		databaseChoice.setBounds(WIDTH*1/10, HEIGHT/2 , 100, 100);
		frame.add(databaseChoice);
		
		gui.setBounds(WIDTH*4/10, HEIGHT/3, 350, 460);
		
		frame.add(gui);
		frame.add(buildmode);

		frame.setVisible(true);
		
		
	}
	
	public Frame() {}

	public Frame(Dimension screenSize, String title, Main mainGame) {
		isBuildMode= false;
		
		frame = new JFrame(title);
		frame.setVisible(true);
		frame.setSize(WIDTH, HEIGHT);
		frame.setBackground(Color.BLACK);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePanel.setBackground(Color.WHITE);
		
		gamePanel.add(Game);
		gamePanel.add(buildMode);
		frame.add(gamePanel);
		
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}


	public JButton getQuitButton() {
		return quitButton;
	}

	public void setQuitButton(JButton quitButton) {
		this.quitButton = quitButton;
	}

	
	public JButton getGame() {
		return Game;
	}

	public void setGame(JButton game) {
		Game = game;
	}

	public JButton getBuildMode() {
		return buildMode;
	}

	public void setBuildMode(JButton buildMode) {
		this.buildMode = buildMode;
	}

	public void dispose() {
		frame.dispose();
	}
	
	public StatisticsWindow getStatsWindow() {
		return statsWindow;
	}

	public void setStatsWindow(StatisticsWindow statsWindow) {
		this.statsWindow = statsWindow;
	}

	public JButton getApplyButton() {
		return applyButton;
	}

	public void setApplyButton(JButton applyButton) {
		this.applyButton = applyButton;
	}
	
	public JTextField getAlphaMoleculeType() {
		return alphaMoleculeType;
	}


	public void setAlphaMoleculeType(JTextField alphaMoleculeType) {
		this.alphaMoleculeType = alphaMoleculeType;
	}


	public JTextField getAlphaMoleculeCount() {
		return alphaMoleculeCount;
	}


	public void setAlphaMoleculeCount(JTextField alphaMoleculeCount) {
		this.alphaMoleculeCount = alphaMoleculeCount;
	}


	public JTextField getBetaMoleculeType() {
		return betaMoleculeType;
	}


	public void setBetaMoleculeType(JTextField betaMoleculeType) {
		this.betaMoleculeType = betaMoleculeType;
	}


	public JTextField getSigmaMoleculeType() {
		return sigmaMoleculeType;
	}


	public void setSigmaMoleculeType(JTextField sigmaMoleculeType) {
		this.sigmaMoleculeType = sigmaMoleculeType;
	}


	public JTextField getGammaMoleculeType() {
		return gammaMoleculeType;
	}


	public void setGammaMoleculeType(JTextField gammaMoleculeType) {
		this.gammaMoleculeType = gammaMoleculeType;
	}


	public JTextField getBetaMoleculeCount() {
		return betaMoleculeCount;
	}


	public void setBetaMoleculeCount(JTextField betaMoleculeCount) {
		this.betaMoleculeCount = betaMoleculeCount;
	}


	public JTextField getSigmaMoleculeCount() {
		return sigmaMoleculeCount;
	}


	public void setSigmaMoleculeCount(JTextField sigmaMoleculeCount) {
		this.sigmaMoleculeCount = sigmaMoleculeCount;
	}


	public JTextField getGammaMoleculeCount() {
		return gammaMoleculeCount;
	}


	public void setGammaMoleculeCount(JTextField gammaMoleculeCount) {
		this.gammaMoleculeCount = gammaMoleculeCount;
	}


	public JTextField getAlphaAtomCount() {
		return alphaAtomCount;
	}


	public void setAlphaAtomCount(JTextField alphaAtomCount) {
		this.alphaAtomCount = alphaAtomCount;
	}


	public JTextField getBetaAtomCount() {
		return betaAtomCount;
	}


	public void setBetaAtomCount(JTextField betaAtomCount) {
		this.betaAtomCount = betaAtomCount;
	}


	public JTextField getSigmaAtomCount() {
		return sigmaAtomCount;
	}


	public void setSigmaAtomCount(JTextField sigmaAtomCount) {
		this.sigmaAtomCount = sigmaAtomCount;
	}


	public JTextField getGammaAtomCount() {
		return gammaAtomCount;
	}


	public void setGammaAtomCount(JTextField gammaAtomCount) {
		this.gammaAtomCount = gammaAtomCount;
	}


	public JTextField getPuCount() {
		return alphaPuCount;
	}
	public void setPuCount(JTextField puCount) {
		this.alphaPuCount = puCount;
	}
	
	public boolean isBuildMode() {
		return isBuildMode;
	}


	public ButtonGroup getbGroup() {
		return bGroup;
	}


	public void setbGroup(ButtonGroup bGroup) {
		this.bGroup = bGroup;
	}


	public JRadioButton getEasyBox() {
		return easyBox;
	}


	public JRadioButton getMediumBox() {
		return mediumBox;
	}


	public JRadioButton getHardBox() {
		return hardBox;
	}


	public JTextField getGameTime() {
		return gameTime;
	}


	public void setGameTime(JTextField gameTime) {
		this.gameTime = gameTime;
	}


	public JTextField getEtaCount() {
		return etaCount;
	}


	public void setEtaCount(JTextField etaCount) {
		this.etaCount = etaCount;
	}


	public JTextField getLotaCount() {
		return lotaCount;
	}


	public void setLotaCount(JTextField lotaCount) {
		this.lotaCount = lotaCount;
	}


	public JTextField getThetaCount() {
		return thetaCount;
	}


	public void setThetaCount(JTextField thetaCount) {
		this.thetaCount = thetaCount;
	}


	public JTextField getZetaCount() {
		return zetaCount;
	}


	public void setZetaCount(JTextField zetaCount) {
		this.zetaCount = zetaCount;
	}


	public JTextField getUsername() {
		return username;
	}


	public void setUsername(JTextField username) {
		this.username = username;
	}


	public JButton getLogin() {
		return login;
	}


	public void setLogin(JButton login) {
		this.login = login;
	}


	public JTextField getLengthL() {
		return lengthL;
	}


	public void setLengthL(JTextField lengthL) {
		this.lengthL = lengthL;
	}


	public JTextField getAlphaPuCount() {
		return alphaPuCount;
	}


	public void setAlphaPuCount(JTextField alphaPuCount) {
		this.alphaPuCount = alphaPuCount;
	}


	public JTextField getBetaPuCount() {
		return betaPuCount;
	}


	public void setBetaPuCount(JTextField betaPuCount) {
		this.betaPuCount = betaPuCount;
	}


	public JTextField getGammaPuCount() {
		return gammaPuCount;
	}


	public void setGammaPuCount(JTextField gammaPuCount) {
		this.gammaPuCount = gammaPuCount;
	}


	public JTextField getSigmaPuCount() {
		return sigmaPuCount;
	}


	public void setSigmaPuCount(JTextField sigmaPuCount) {
		this.sigmaPuCount = sigmaPuCount;
	}


	public JTextField getAblockerCount() {
		return ablockerCount;
	}


	public void setAblockerCount(JTextField ablockerCount) {
		this.ablockerCount = ablockerCount;
	}


	public JTextField getBblockerCount() {
		return bblockerCount;
	}


	public void setBblockerCount(JTextField bblockerCount) {
		this.bblockerCount = bblockerCount;
	}


	public JTextField getSblockerCount() {
		return sblockerCount;
	}


	public void setSblockerCount(JTextField sblockerCount) {
		this.sblockerCount = sblockerCount;
	}


	public JTextField getGblockerCount() {
		return gblockerCount;
	}


	public void setGblockerCount(JTextField gblockerCount) {
		this.gblockerCount = gblockerCount;
	}


	public ButtonGroup getDBGroup() {
		return DBGroup;
	}


	public void setDBGroup(ButtonGroup dBGroup) {
		DBGroup = dBGroup;
	}
	
}