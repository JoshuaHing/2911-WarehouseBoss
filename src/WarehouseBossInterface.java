import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class WarehouseBossInterface extends JFrame implements ActionListener, KeyListener {
	// We want to bulid interface with basic steps including
	// first pass, next level, previous level, last level and undo the last
	// move;
	JButton WbFirst, WbNext, WbPre, WbFinal, WbUndo;

	// Then we can provide more actions like select which level gamer want to
	// challenge
	// and restart this level's game, Exit game.
	JButton WbSelect, WbRestart, WbExit;

	// Final, If we can, we can play a background music in our game.
	// JButton WbMusic;

	ArrayList<ArrayList<String>> map;
	Game game;
	// Create MyPanel
	MyPanel mainPanel;
	JLabel label;
	boolean leftPressed;
	boolean rightPressed;
	boolean upPressed;
	boolean downPressed;
	private int numGoals;
	private static final int MODE_REFRESH = 0;
	private static final int MODE_RESTART = 1;
	private static final int MODE_DONE = 2;
	public WarehouseBossInterface(Game game, int numGoals) {
		super("Warehouse Boss 2017-COMP2911");
		//game = new Game();
		this.map = new ArrayList<ArrayList<String>>();
		/** code for arrow keys */
		JPanel p = new JPanel();
		label = new JLabel("Key Listener!");
		p.add(label);
		add(p);
		addKeyListener(this);
		setSize(0, 0);
		setVisible(true);
		
		leftPressed = true;
		rightPressed = true;
		upPressed = true;
		downPressed = true;
		this.game = game;
		this.numGoals = numGoals;
		// end of code for arrow keys
		//map = new ArrayList<ArrayList<String>>();
		// This is going to set Icon of this game
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("img/box.png");
		setIconImage(image);

		// This is going to set the background of this game
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.white);

		// Set the title of this game in the interface
		JLabel WbTile = new JLabel("Warehouse Boss 2017-COMP2911", JLabel.CENTER);
		WbTile.setFont(new Font("American Typewriter", Font.BOLD, 16));
		WbTile.setBounds(100, 20, 500, 30);
		add(WbTile, BorderLayout.NORTH);

		// Set buttons' location
		setButtonLocation(c);

		mainPanel = new MyPanel(game.getMap());
		mainPanel.setBounds(200, 150, 400, 400);
		c.add(mainPanel);
		setSize(720, 720);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setButtonLocation(Container c) {

		// This part is going to Set Button in right side of interface

		WbUndo = new JButton("Undo");
		WbFirst = new JButton("First Level");
		WbNext = new JButton("Next Level");
		WbPre = new JButton("Previous Level");
		WbSelect = new JButton("Select Level");
		WbFinal = new JButton("Final Level");
		WbRestart = new JButton("Restart");
		WbExit = new JButton("Exit");
		// WbMusic = new JButton("Music");

		// set the locationn of each button
		WbUndo.setBounds(600, 80, 120, 30);
		WbFirst.setBounds(600, 130, 120, 30);
		WbNext.setBounds(600, 180, 120, 30);
		WbPre.setBounds(600, 230, 120, 30);
		WbSelect.setBounds(600, 280, 120, 30);
		WbFinal.setBounds(600, 330, 120, 30);
		WbRestart.setBounds(600, 380, 120, 30);
		WbExit.setBounds(600, 430, 120, 30);

		// set the function of each button
		WbUndo.addActionListener(this);
		WbFirst.addActionListener(this);
		WbNext.addActionListener(this);
		WbPre.addActionListener(this);
		WbSelect.addActionListener(this);
		WbFinal.addActionListener(this);
		WbRestart.addActionListener(this);
		WbExit.addActionListener(this);

		// Let buttons display on the screen
		c.add(WbUndo);
		c.add(WbFirst);
		c.add(WbNext);
		c.add(WbPre);
		c.add(WbSelect);
		c.add(WbFinal);
		c.add(WbRestart);
		c.add(WbExit);

	}

	@Override
	// This part is going to set the function of each button...
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == WbExit) {
			String str = "Are you sure you want to quit?\n";
			JOptionPane.showMessageDialog(this, str,"Warnnig", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		} else if(e.getSource() == WbRestart) {
			this.game.setMap(this.game.getInitialMap());	//**IN PROGRESS**
			this.updateInterface(1, this.game);				//When the restart button is pressed, the game should go back to the start.
		}
	}

	// Main game interface
	public class MyPanel extends JPanel {
		Toolkit kit = Toolkit.getDefaultToolkit();
		ArrayList<ArrayList<String>>map;
		Image mapimg[] = {
				kit.getImage("img/box.png"), 
				kit.getImage("img/goal.png"),
				kit.getImage("img/ground.png"), 
				kit.getImage("img/player.png"), 
				kit.getImage("img/wall.png"), 
				kit.getImage("img/goal-box.png")};

		public MyPanel(ArrayList<ArrayList<String>>map) {
			setSize(640, 640);
			this.map = map;
			requestFocus();
		}

		@Override
		public void paint(Graphics g) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					String currString = this.map.get(j).get(i);
					if (currString.equals("B")) {	//Box
						g.drawImage(mapimg[0], i * 32, j * 32, 32, 32, this);
					} else if (currString.equals("T")) {	//goal square
						g.drawImage(mapimg[1], i * 32, j * 32, 32, 32, this);
					} else if (currString.equals("E")) {	//empty
						g.drawImage(mapimg[2], i * 32, j * 32, 32, 32, this);
					} else if (currString.equals("O")) {	//player
						g.drawImage(mapimg[3], i * 32, j * 32, 32, 32, this);
					} else if (currString.equals("P")) {	//player
						g.drawImage(mapimg[3], i * 32, j * 32, 32, 32, this);
					} else if (currString.equals("W")) {	//wall
						g.drawImage(mapimg[4], i * 32, j * 32, 32, 32, this);
					} else if (currString.equals("D")) {	//box is on goal square
						g.drawImage(mapimg[5], i * 32, j * 32, 32, 32, this);
					}
				}
			}
		}
	}
	
	public void updateInterface(int mode, Game game) {
		mainPanel.removeAll();
		if(mode == MODE_REFRESH) {	//for refreshing
			mainPanel.add(new MyPanel(game.getMap()));
		} else if(mode == MODE_RESTART) { //for going back to start ***IN PROGRESS***
			mainPanel.add(new MyPanel(game.getInitialMap()));
		} else if(mode == MODE_DONE) {		//for when the level finishes ***IN PROGRESS***
			//create a window to be printed 
			mainPanel.add(new MyPanel(game.getMap()));
			/*JPanel newPanel = new JPanel();
			String message = "Congratulations!";*/
		}
		mainPanel.validate();
	}

	public void left() {
		System.out.println("move left");
		this.game.moveLEFT();
		if(this.game.checkIfDone(this.numGoals)) {
			this.updateInterface(MODE_DONE, this.game);
		} else {
			this.updateInterface(MODE_REFRESH, this.game);
		}
	}

	public void right() {
		System.out.println("move right");
		this.game.moveRIGHT();
		this.game.checkIfDone(this.numGoals);
		if(this.game.checkIfDone(this.numGoals)) {
			this.updateInterface(MODE_DONE, this.game);
		} else {
			this.updateInterface(MODE_REFRESH, this.game);
		}
	}

	public void up() {
		System.out.println("move up");
		this.game.moveUP();
		if(this.game.checkIfDone(this.numGoals)) {
			this.updateInterface(MODE_DONE, this.game);
		} else {
			this.updateInterface(MODE_REFRESH, this.game);
		}
	}

	public void down() {
		System.out.println("move down");
		this.game.moveDOWN();
		if(this.game.checkIfDone(this.numGoals)) {
			this.updateInterface(MODE_DONE, this.game);
		} else {
			this.updateInterface(MODE_REFRESH, this.game);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// debug key type System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());

		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_LEFT:
				//move left;
				left();
				break;
			case KeyEvent.VK_RIGHT:
				//move right
				right();
				break;
			case KeyEvent.VK_UP:
				//move up
				up();
				break;
			case KeyEvent.VK_DOWN:
				//move down
				down();
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}
