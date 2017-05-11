import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

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

	Game game;
	// Create MyPanel
	MyPanel mainPanel;
	JLabel label;
	boolean leftPressed;
	boolean rightPressed;
	boolean upPressed;
	boolean downPressed;
	private int numGoals;
	private int currLevel;
	private static final int MODE_REFRESH = 0;			//Different modes for refreshing the interface
	private static final int MODE_RESTART = 1;
	private static final int MODE_DONE = 2;
	private static final int MAP_SIZE = 10;				//Will be removed once we implement auto-generated maps
	
	public WarehouseBossInterface(Game game) {
		super("Warehouse Boss 2017-COMP2911");			//String of the title bar
		/* start code for arrow keys */
		JPanel p = new JPanel();						//Create a new JPanel
		label = new JLabel("Key Listener!");
		/*p.add(label);
		add(p);											//Is this code necessary?
		*/
		addKeyListener(this);
		setSize(0, 0);
		setVisible(true);
		leftPressed = true;
		rightPressed = true;
		upPressed = true;
		downPressed = true;
		
		this.game = game;								//Give the interface the game. The game contains all of the game maps.
		this.currLevel = 0;
		/* end of code for arrow keys */

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

		// mainPanel contains the game map. When it is instantiated, the images are automatically loaded onto the panel via the "paint" method.
		mainPanel = new MyPanel(game.getMap());	
		mainPanel.setBounds(150, 150, 400, 400);	//(x-position, y-position, width, height)
		c.add(mainPanel);
		setSize(720, 720);							//set size of the entire container
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

		// set the location of each button
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
		if (e.getSource() == WbExit) {				//When exit button is pressed, warning message shows up.
			String str = "Are you sure you want to exit?\n";
			JOptionPane.showMessageDialog(this, str,"Warning", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		} else if(e.getSource() == WbRestart) {
			System.out.println("hello!!");
			this.game.setMap(this.game.getInitialMap());	//**NOT YET IMPLEMENTED**
			this.updateInterface(MODE_REFRESH, this.game);				//When the restart button is pressed, the game should go back to the start.
		}
	}

	// Main game interface
	public class MyPanel extends JPanel {
		Toolkit kit = Toolkit.getDefaultToolkit();
		ArrayList<ArrayList<String>>map;
		Image mapimg[] = {						//Gets the image files from "img" folder 
				kit.getImage("img/box.png"), 
				kit.getImage("img/goal.png"),
				kit.getImage("img/ground.png"), 
				kit.getImage("img/player.png"), 
				kit.getImage("img/wall.png"), 
				kit.getImage("img/goal-box.png")};		//More images can be added
					
		public MyPanel(ArrayList<ArrayList<String>> map) {
			setSize(640, 640);
			this.map = map;
			requestFocus();
		}

		@Override
		public void paint(Graphics g) {
			ArrayList<ArrayList<String>> currMap = game.getLevel(currLevel).getMap();	//Get the map representing the current level that the player is on
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					String currString = currMap.get(j).get(i);
					if (currString.equals("B")) {	//Box
						g.drawImage(mapimg[0],  i * 40, j * 40, 40, 40, this);
					} else if (currString.equals("T")) {	//goal square
						g.drawImage(mapimg[1],  i * 40, j * 40, 40, 40, this);
					} else if (currString.equals("E")) {	//empty
						g.drawImage(mapimg[2],  i * 40, j * 40, 40, 40, this);
					} else if (currString.equals("O")) {	//player
						g.drawImage(mapimg[3],  i * 40, j * 40, 40, 40, this);
					} else if (currString.equals("P")) {	//player				//Probably not needed
						g.drawImage(mapimg[3],  i * 40, j * 40, 40, 40, this);
					} else if (currString.equals("W")) {	//wall
						g.drawImage(mapimg[4],  i * 40, j * 40, 40, 40, this);
					} else if (currString.equals("D")) {	//box is on goal square
						g.drawImage(mapimg[5],  i * 40, j * 40, 40, 40, this);
					}
				}
			}
		}
	}
	
	public void updateInterface(int mode, Game game) {	//This method displays a new version of the map (once a move has been made)
		mainPanel.removeAll();										
		if(mode == MODE_REFRESH) {						//Used for general purposes 
			mainPanel.add(new MyPanel(game.getMap()));					
		} else if(mode == MODE_RESTART) { 				//**YET TO BE IMPLEMENTED**
			currLevel = 0;								//Used for when the restart button is pressed	
			mainPanel.add(new MyPanel(game.getInitialMap()));
		} else if(mode == MODE_DONE) {		//**YET TO BE IMPLEMENTED**
			//The program should display a closing message such as "Congratulations!" or something
			//And the player should not be able to continue to move
		}
		mainPanel.validate();							
	}

	public void left() {
		System.out.println("move left");				//This line is for testing purposes
		this.game.moveLEFT(this.currLevel);				//Game sends a request to map to check if a move can be made
		this.continueIfDone();
	}

	public void right() {
		System.out.println("move right");
		this.game.moveRIGHT(this.currLevel);
		this.continueIfDone();
	}

	public void up() {
		System.out.println("move up");
		this.game.moveUP(this.currLevel);
		this.continueIfDone();
	}

	public void down() {
		System.out.println("move down");
		this.game.moveDOWN(this.currLevel);
		this.continueIfDone();
	}
	
	public void continueIfDone() {
		if(game.getLevel(currLevel).isDone()) {	//If the game is done
			if(game.hasNextLevel(currLevel)) {	//AND the game has another level
				currLevel++;					//	THEN move to the next level and refresh the inteface
				updateInterface(MODE_REFRESH, game);
			} else {							//ELSE finish the game
				updateInterface(MODE_DONE, game); //**YET TO BE IMPLEMENTED**
			}
		} else {	//If the game isn't done, continue.
			this.updateInterface(MODE_REFRESH, game);
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
