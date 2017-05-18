import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class WarehouseBossInterface extends JFrame implements ActionListener, KeyListener, ItemListener {
	// We want to bulid ipnterface with basic steps including
	// first pass, next level, previous level, last level and undo the last
	// move;
	JButton WbFirst, WbNext, WbPre, WbTimer, WbUndo;

	// Then we can provide more actions like select which level gamer want to
	// challenge
	// and restart this level's game, Exit game.
	JButton WbSelect, WbReset, WbExit;

	// Final, If we can, we can play a background music in our game.
	JButton WbMusicOn;
	JComboBox cbMusic;

	String sMusicFiles[] = { "He's a Pirate.midi", "Snow Dream-Bandari.midi" };
	String sMusicList[] = { "He's a Pirate", "Snow Deam" };

	JMenuItem music1, music2;

	//Create MyPanel
	MyPanel mainPanel;
	
	//Create pic
    Animation.pic canvas = null;


	JLabel label;
	boolean leftPressed;
	boolean rightPressed;
	boolean upPressed;
	boolean downPressed;
	private int numGoals;
	public static int currLevel;
	int seconds1;
	int seconds2;
	int milliseconds;
	int tenthSecond;
	int minutes1;
	int minutes2;
	private static final int PLAYER_ONE = 1;
	private static final int PLAYER_TWO = 2;
	private static final int MODE_REFRESH = 0; // Different modes for refreshing
												// the interface
	private static final int MODE_RESTART = 1;
	private static final int MODE_DONE = 2;
	private static final int MAP_SIZE = 10; // Will be removed once we implement
											// auto-generated maps
	private StartMenu startMenu;
	private Music music;

	@Override
	public void itemStateChanged(ItemEvent e) {
		int index = cbMusic.getSelectedIndex();
		String sfileName = sMusicFiles[index];
		music.setMusic(sfileName);
		if (music.isPlay()) {
			music.stopPlay();
		}
		music.loadMusic();
	}

	public enum STATE {
		MENU, GAME
	}

	public static STATE state = STATE.GAME;

	public WarehouseBossInterface(Game game) {
		super("Warehouse Boss 2017-COMP2911"); // String of the title bar
		/* start code for arrow keys */
		JPanel p = new JPanel(); // Create a new JPanel
		label = new JLabel("Key Listener!");
		/*
		 * p.add(label); add(p); //Is this code necessary?
		 */
		addKeyListener(this);
		setSize(0, 0);
		setVisible(true);
		leftPressed = true;
		rightPressed = true;
		upPressed = true;
		downPressed = true;

		this.game = game; // Give the interface the game. The game contains all
							// of the game maps.
		
		this.currLevel = 0;

		this.seconds1 = 0;
		this.seconds2 = 0;
		this.milliseconds = 0;
		this.tenthSecond = 0;
		this.minutes1 = 0;
		this.minutes2 = 0;

		startMenu = new StartMenu();
		this.addMouseListener(new MouseInput());

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
		// mainPanel contains the game map. When it is instantiated, the images
		// are automatically loaded onto the panel via the "paint" method.
		mainPanel = new MyPanel(game.getMap());
		mainPanel.setBounds(150, 150, 400, 400); // (x-position, y-position,
									
		canvas = new Animation.pic (game.getMap(), game);
		canvas.setBounds(850, 120, 880, 440);
		
	    c.add (canvas);
		
		// width, height)
		c.add(mainPanel);
		setSize(1820, 820); // set size of the entire container
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		music = new Music();
		// mainPanel.requestFocus();
	}

	public void setButtonLocation(Container c) {
		// this.addMouseListener(new MouseInput());
		if (state == STATE.GAME) {

			// This part is going to Set Button in right side of interface

			WbUndo = new JButton("Undo");
			WbFirst = new JButton("First Level");
			WbNext = new JButton("Next Level");
			WbPre = new JButton("Previous Level");
			WbSelect = new JButton("Select Level");
			WbTimer = new JButton("Timer");
			WbReset = new JButton("Reset");
			WbMusicOn = new JButton("Music On");
			WbExit = new JButton("Exit");
			JLabel musicList = new JLabel("Choose Music");
			cbMusic = new JComboBox(sMusicList);

			// set the location of each button
			WbUndo.setBounds(600, 80, 120, 30);
			WbFirst.setBounds(600, 130, 120, 30);
			WbNext.setBounds(600, 180, 120, 30);
			WbPre.setBounds(600, 230, 120, 30);
			WbSelect.setBounds(600, 280, 120, 30);
			WbTimer.setBounds(600, 330, 120, 30);
			WbReset.setBounds(600, 380, 120, 30);
			WbMusicOn.setBounds(600, 430, 120, 30);
			musicList.setBounds(615, 470, 120, 30);
			cbMusic.setBounds(600, 490, 120, 40);
			WbExit.setBounds(600, 540, 120, 30);

			// set the function of each button
			WbUndo.addActionListener(this);
			WbFirst.addActionListener(this);
			WbNext.addActionListener(this);
			WbPre.addActionListener(this);
			WbSelect.addActionListener(this);
			WbTimer.addActionListener(this);
			WbReset.addActionListener(this);
			WbMusicOn.addActionListener(this);
			cbMusic.addItemListener(this);
			WbExit.addActionListener(this);

			// Let buttons display on the screen
			c.add(WbUndo);
			c.add(WbFirst);
			c.add(WbNext);
			c.add(WbPre);
			c.add(WbSelect);
			c.add(WbTimer);
			c.add(WbReset);
			c.add(WbExit);
			c.add(WbMusicOn);
			c.add(musicList);
			c.add(cbMusic);
		}

	}

	@Override
	// This part is going to set the function of each button...
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == WbExit) { // When exit button is pressed, warning
										// message shows up.
			String str = "Are you sure you want to exit?\n";
			int diaResult = JOptionPane.showConfirmDialog(this, str, "Warning", JOptionPane.YES_NO_OPTION);
			if (diaResult == JOptionPane.YES_OPTION) {
				System.exit(0);
			} else {
				requestFocus();
			}
		} else if (e.getSource() == WbReset) {
			// When the restart button is pressed, the game should go back to
			// the start.
			String str = "Do you want to restart this level?\n";
			int diaResult = JOptionPane.showConfirmDialog(this, str, "Warning", JOptionPane.YES_NO_OPTION);
			if (diaResult == JOptionPane.YES_OPTION) {
				updateInterface(MODE_RESTART, game);
				// Not finish Part right now
				// mainPanel.removeAll();
				// papi = new MyPanel();
				requestFocus();
			} else {
				requestFocus();
			}

		} else if (e.getSource() == WbTimer) {
			JFrame frame = new JFrame();
			int FIELD_WIDTH = 20;
			JTextField textField = new JTextField(FIELD_WIDTH);

			frame.setLayout(new FlowLayout());
			frame.add(textField);

			ActionListener listener = new ActionListener() {
				public void actionPerformed(ActionEvent event) {

					String s = " " + minutes2 + minutes1 + ":" + seconds2 + seconds1;
					if (tenthSecond == 10) {
						seconds1++;
						tenthSecond = 0;
						if (seconds1 == 10) {
							seconds2++;
							seconds1 = 0;
						}
					}
					if (seconds2 == 6 && seconds1 == 0) {
						minutes1++;
						seconds2 = 0;
						seconds1 = 0;
						if (minutes1 == 10) {
							minutes2++;
							minutes1 = 0;
						}
					}
					textField.setText(s);
					tenthSecond++;
				}
			};
			int DELAY = 100;
			Timer t = new Timer(DELAY, listener);
			t.start();
			frame.pack();
			frame.setVisible(true);
			frame.setLocation(350, 170);
			requestFocus();
		} else if (e.getSource() == WbSelect) {
			String selectLevel = JOptionPane.showInputDialog(this, "Which level do you want to play ?", "Level",
					JOptionPane.QUESTION_MESSAGE);
			int selectedLevel = Integer.parseInt(selectLevel)-1;
			game.resetMap(currLevel);
			currLevel = selectedLevel;
			updateInterface(MODE_REFRESH, game);

			requestFocus();
		} else if (e.getSource() == WbPre) {
			requestFocus();
		} else if (e.getSource() == WbNext) {
			requestFocus();
		} else if (e.getSource() == WbFirst) {
			requestFocus();
		} else if (e.getSource() == WbUndo) {
			requestFocus();
		} else if (e.getSource() == WbMusicOn) {
			String title = WbMusicOn.getText();
			if (title.equals("Music On")) {
				music.stopPlay();
				WbMusicOn.setText("Music Off");
			} else if (title.equals("Music Off")) {
				music.loadMusic();
				WbMusicOn.setText("Music On");
			}
			requestFocus();
		}
	}

	// Main game interface
	public class MyPanel extends JPanel {
		Toolkit kit = Toolkit.getDefaultToolkit();
		ArrayList<ArrayList<String>> map;
		Image mapimg[] = { // Gets the image files from "img" folder
				kit.getImage("img/box1.png"), 
				kit.getImage("img/goal1.png"), 
				kit.getImage("img/ground1.png"),
				kit.getImage("img/player.png"), 
				kit.getImage("img/wall1.png"), 
				kit.getImage("img/goal-box1.png") }; // More
																													// images
																													// can
																													// be
																													// added

		public MyPanel(ArrayList<ArrayList<String>> map) {
			setSize(640, 640);
			this.map = map;
			requestFocus();
		}

		public void paint(Graphics g) {
			// System.out.println("in paint " + state);
			if (state == STATE.GAME) {
				// new WarehouseBossInterface(game);
				// System.out.println("in paint2 " + state);
				ArrayList<ArrayList<String>> currMap = game.getLevel(currLevel).getMap(); // Get the map representing the current level.
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						String currString = currMap.get(j).get(i);
						if (currString.equals("B")) { // Box
							g.drawImage(mapimg[0], i * 40, j * 40, 40, 40, this);
						} else if (currString.equals("T")) { // goal square
							g.drawImage(mapimg[1], i * 40, j * 40, 40, 40, this);
						} else if (currString.equals("E")) { // empty
							g.drawImage(mapimg[2], i * 40, j * 40, 40, 40, this);
						} else if (currString.equals("P")||currString.equals("Q")||currString.equals("O")||currString.equals("R")) { // player Probably not needed
							g.drawImage(mapimg[3], i * 40, j * 40, 40, 40, this);
							Animation.goalX = i * 40;
							Animation.goalY = j * 40;
						} else if (currString.equals("W")) { // wall
							g.drawImage(mapimg[4], i * 40, j * 40, 40, 40, this);
						} else if (currString.equals("D")) { // box is on goal
							g.drawImage(mapimg[5], i * 40, j * 40, 40, 40, this);
						}
					}
				}
			} else if (state == STATE.MENU) {
				startMenu.render(g);
			}
		}
	}

	public void updateInterface(int mode, Game game) { // This method displays a
														// new version of the
														// map (once a move has
														// been made)
		mainPanel.removeAll();
		if (mode == MODE_REFRESH) { // Used for general purposes
			mainPanel.add(new MyPanel(game.getMap()));
		} else if (mode == MODE_RESTART) { // **YET TO BE IMPLEMENTED**
			currLevel = 0; // Used for when the restart button is pressed
			mainPanel.add(new MyPanel(game.getMap()));
		} else if (mode == MODE_DONE) { // **YET TO BE IMPLEMENTED**
			// The program should display a closing message such as
			// "Congratulations!" or something
			// And the player should not be able to continue to move
		}
		mainPanel.validate();
	}

	public void left(int player) {
		// System.out.println("move left"); //This line is for testing purposes
		if (player == PLAYER_ONE) {
			this.game.moveLEFT(this.currLevel, PLAYER_ONE);
		} else if (player == PLAYER_TWO) {
			this.game.moveLEFT(this.currLevel, PLAYER_TWO);
		} // Game sends a request to map to check if a move can be made
		this.continueIfDone();
	}

	public void right(int player) {
		// System.out.println("move right");
		if (player == PLAYER_ONE) {
			this.game.moveRIGHT(this.currLevel, PLAYER_ONE);
		} else if (player == PLAYER_TWO) {
			this.game.moveRIGHT(this.currLevel, PLAYER_TWO);
		}
		this.continueIfDone();
	}

	public void up(int player) {
		// System.out.println("move up");
		if (player == PLAYER_ONE) {
			this.game.moveUP(this.currLevel, PLAYER_ONE);
		} else if (player == PLAYER_TWO) {
			this.game.moveUP(this.currLevel, PLAYER_TWO);
		}
		this.continueIfDone();
	}

	public void down(int player) {
		if (player == PLAYER_ONE) {
			this.game.moveDOWN(this.currLevel, PLAYER_ONE);
		} else if (player == PLAYER_TWO) {
			this.game.moveDOWN(this.currLevel, PLAYER_TWO);
		}
		this.continueIfDone();
	}
	
	public void continueIfDone() {
		if (game.getLevel(currLevel).isDone()) { // If the game is done
			System.out.println("currLevel = " + currLevel);
			game.resetMap(currLevel);
			if (game.hasNextLevel(currLevel)) { // AND the game has another
												// level
				currLevel++; // THEN move to the next level and refresh the interface
				updateInterface(MODE_REFRESH, game);
			} else { // ELSE finish the game
				//System.out.println("done.");
				//replace the current panel with a closing message
				//System.exit(0);
				updateInterface(MODE_DONE, game); // **YET TO BE IMPLEMENTED**F
			}
		} else { // If the game isn't done, continue.
			this.updateInterface(MODE_REFRESH, game);

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// debug key type System.out.println("Key pressed code=" +
		// e.getKeyCode() + ", char=" + e.getKeyChar());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Animation.PRESSED = true;
		Animation.DIR = e.getKeyCode();
		// System.out.println("in keypressed " + state);
		if (state == STATE.GAME) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				// move left;
				left(PLAYER_ONE);
				break;
			case KeyEvent.VK_RIGHT:
				// move right
				right(PLAYER_ONE);
				break;
			case KeyEvent.VK_UP:
				// move up
				up(PLAYER_ONE);
				break;
			case KeyEvent.VK_DOWN:
				// move down
				down(PLAYER_ONE);
				break;
			case KeyEvent.VK_A:
				// move left;
				left(PLAYER_TWO);
				break;
			case KeyEvent.VK_D:
				// move right
				right(PLAYER_TWO);
				break;
			case KeyEvent.VK_W:
				// move up
				up(PLAYER_TWO);
				break;
			case KeyEvent.VK_S:
				// move down
				down(PLAYER_TWO);
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Animation.RIGHT_GO = 0;
		Animation.PRESSED = false;
	}

	public Game game;
	
	private Animation animation;
}
