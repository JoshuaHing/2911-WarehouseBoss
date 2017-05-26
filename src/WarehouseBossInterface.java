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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class WarehouseBossInterface extends JFrame implements ActionListener, KeyListener, ItemListener, MouseListener {
	// We want to bulid ipnterface with basic steps including
	// first pass, next level, previous level, last level and undo the last
	// move;
	JButton WbFirst, WbNext, WbPre, WbTimer, WbUndo, help;

	// Then we can provide more actions like select which level gamer want to
	// challenge
	// and restart this level's game, Exit game.
	JButton WbMenu, WbReset, WbQuit;

	// Final, If we can, we can play a background music in our game.
	JButton WbMusicOn;
	JComboBox<String> cbMusic;

	String sMusicFiles[] = { "He's a Pirate.midi", "Snow Dream-Bandari.midi" };
	String sMusicList[] = { "He's a Pirate", "Snow Deam" };

	JMenuItem music1, music2;

	JPanel timerPanel;
	//Create MyPanel
	MyPanel mainPanel;
	
	//Create pic
    Animation.pic canvas = null;


	JLabel label;
	boolean leftPressed;
	boolean rightPressed;
	boolean upPressed;
	boolean downPressed;
	
	boolean timerOn = false;
	boolean keyReleased = true;
	
	private int numGoals;

	public static int currLevel;
	int seconds1;
	int seconds2;
	int milliseconds;
	int tenthSecond;
	int minutes1;
	int minutes2;
	int secondsPlayed;
	
	private static final int NUM_ROWS = 12;
	private static final int NUM_COLS = 12;
	private static final int PLAYER_ONE = 1;
	private static final int PLAYER_TWO = 2;
	private static final int MODE_REFRESH = 0; // Different modes for refreshing
												// the interface
	private static final int MODE_RESTART = 1;
	private static final int MODE_DONE = 2;
	private static final int MAP_SIZE = 12; // Will be removed once we implement
											// auto-generated maps
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


	public WarehouseBossInterface(Game game) {
		super("Warehouse Boss 2017-COMP2911"); // String of the title bar
		/* start code for arrow keys */
		JPanel p = new JPanel(); // Create a new JPanel
		label = new JLabel("Key Listener!");
		/*
		 * p.add(label); add(p); //Is this code necessary?
		 */
		addKeyListener(this);
		//addMouseListener(this);
		
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
		this.secondsPlayed = 0;

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
		/*JLabel WbTile = new JLabel("Warehouse Boss 2017-COMP2911", JLabel.CENTER);
		WbTile.setFont(new Font("American Typewriter", Font.BOLD, 16));
		WbTile.setBounds(100, 20, 500, 30);
		add(WbTile, BorderLayout.NORTH);*/

		// Set buttons' location
		setButtonLocation(c);
		// mainPanel contains the game map. When it is instantiated, the images
		// are automatically loaded onto the panel via the "paint" method.
		mainPanel = new MyPanel(game.getMap());
		mainPanel.setBounds(28, 615, 240, 240); // (x-position, y-position,
									
		canvas = new Animation.pic (game.getMap(), game);
		canvas.setBounds(0, 0, 1675, 865);
		
		timerPanel = new JPanel();
		int FIELD_WIDTH = 10;
		JTextField textField = new JTextField(FIELD_WIDTH);
		Font font = new Font("Courier", Font.BOLD,16);
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setFont(font);

		timerPanel.setLayout(new FlowLayout());
		timerPanel.add(textField);
		timerPanel.setBackground(new java.awt.Color(224, 131, 38));

		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				String s = "\0" + minutes2 + minutes1 + ":" + seconds2 + seconds1 +"\0";
				if(tenthSecond == 5) {
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
				tenthSecond++;
				textField.setText(s);
				secondsPlayed++;
				//requestFocus();
			}
		};
		int DELAY = 200;
		Timer t = new Timer(DELAY, listener);
		t.start();
		//panel.pack();
	
		timerPanel.setBounds(90,560,110,35);
		timerPanel.setVisible(false);
		c.add(timerPanel);
		mainPanel.addMouseListener(new MouseAdapter() {
            @Override
        	public void mouseClicked(MouseEvent arg0) {
        		requestFocus();
        	}

        	@Override
        	public void mouseEntered(MouseEvent arg0) {
        		requestFocus();
        	}
        	@Override
        	public void mouseExited(MouseEvent arg0) {
        		requestFocus();
        	}
        	@Override
        	public void mousePressed(MouseEvent arg0) {
        		requestFocus();
        	}
        	@Override
        	public void mouseReleased(MouseEvent arg0) {
        		requestFocus();
        		
        	}
        });
		canvas.addMouseListener(new MouseAdapter() {
            @Override
        	public void mouseClicked(MouseEvent arg0) {
        		requestFocus();
        	}

        	@Override
        	public void mouseEntered(MouseEvent arg0) {
        		requestFocus();
        	}
        	@Override
        	public void mouseExited(MouseEvent arg0) {
        		requestFocus();
        	}
        	@Override
        	public void mousePressed(MouseEvent arg0) {
        		requestFocus();
        	}
        	@Override
        	public void mouseReleased(MouseEvent arg0) {
        		requestFocus();
        		
        	}
        });
		c.addMouseListener(new MouseAdapter() {
            @Override
        	public void mouseClicked(MouseEvent arg0) {
        		requestFocus();
        	}

        	@Override
        	public void mouseEntered(MouseEvent arg0) {
        		requestFocus();
        	}
        	@Override
        	public void mouseExited(MouseEvent arg0) {
        		requestFocus();
        	}
        	@Override
        	public void mousePressed(MouseEvent arg0) {
        		requestFocus();
        	}
        	@Override
        	public void mouseReleased(MouseEvent arg0) {
        		requestFocus();
        		
        	}
        });
		
		c.add(mainPanel);
	    c.add (canvas);
	    music = new Music();
		
		// width, height)
	    
		setSize(1678, 936); // set size of the entire container
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setButtonLocation(Container c) {
		// this.addMouseListener(new MouseInput());
		int X = 1410;
		int Y = 200;

		// This part is going to Set Button in right side of interface

		WbUndo = new JButton("Undo");
		WbNext = new JButton("Next Level");
		WbPre = new JButton("Prev Level");
		WbMenu = new JButton("Menu");
		WbTimer = new JButton("Timer Mode");
		WbReset = new JButton("Reset");
		WbMusicOn = new JButton("Music On");
		WbQuit = new JButton("Quit");
		JLabel musicList = new JLabel("Choose Music");
		cbMusic = new JComboBox<>(sMusicList);

		
		// set the location of each button
		WbUndo.setBounds(X, Y+30, 120, 30);
		WbReset.setBounds(X, Y+85, 120, 30);
		
		WbNext.setBounds(X, Y+140, 120, 30);
		WbPre.setBounds(X, Y+195, 120, 30);
		WbTimer.setBounds(X, Y+250, 120, 30);
		
		WbMenu.setBounds(X, Y+305, 120, 30);
		WbQuit.setBounds(X, Y+360, 120, 30);
		
		WbMusicOn.setBounds(X, Y+538, 120, 30);
		//musicList.setBounds(X+10, Y+584, 120, 30);
		cbMusic.setBounds(X, Y+567, 120, 40);
		
		
		Color C = new Color(254,232,156);
		WbUndo.setBackground(C);
		WbUndo.setOpaque(true);
		WbNext.setBackground(C);
		WbNext.setOpaque(true);

		WbPre.setBackground(C);
		WbPre.setOpaque(true);

		WbMenu.setBackground(C);
		WbMenu.setOpaque(true);

		WbTimer.setBackground(C);
		WbTimer.setOpaque(true);

		WbReset.setBackground(C);
		WbReset.setOpaque(true);

		WbMusicOn.setBackground(C);
		WbMusicOn.setOpaque(true);

		WbQuit.setBackground(C);
		WbQuit.setOpaque(true);

		musicList.setBackground(C);
		musicList.setOpaque(true);

		cbMusic.setBackground(C);
		cbMusic.setOpaque(true);


		// set the function of each button
		WbUndo.addActionListener(this);
		WbNext.addActionListener(this);
		WbPre.addActionListener(this);
		WbMenu.addActionListener(this);
		WbTimer.addActionListener(this);
		WbReset.addActionListener(this);
		WbMusicOn.addActionListener(this);
		cbMusic.addItemListener(this);
		WbQuit.addActionListener(this);

		// Let buttons display on the screen
		c.add(WbUndo);
		c.add(WbNext);
		c.add(WbPre);
		c.add(WbMenu);
		c.add(WbTimer);
		c.add(WbReset);
		c.add(WbQuit);
		c.add(WbMusicOn);
		c.add(musicList);
		c.add(cbMusic);
		
		
	}

	@Override
	// This part is going to set the function of each button...
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == WbQuit) { // When exit button is pressed, warning
										// message shows up.
			String str = "Are you sure you want to quit?\n";
			int diaResult = JOptionPane.showConfirmDialog(this, str, "Warning", JOptionPane.YES_NO_OPTION);
			if (diaResult == JOptionPane.YES_OPTION) {
				System.exit(0);
			} else {
				requestFocus();
			}
		} else if (e.getSource() == WbReset) {
			// When the restart button is pressed, the game should go back to
			// the start.
			String str = "Would you like to reset this level?\n";
			int diaResult = JOptionPane.showConfirmDialog(this, str, "Warning", JOptionPane.YES_NO_OPTION);
			if (diaResult == JOptionPane.YES_OPTION) {
				updateInterface(MODE_RESTART, game);
				requestFocus();
			} else {
				requestFocus();
			}
			resetTimer();

		} else if (e.getSource() == WbTimer) {
			if(!timerOn) {
				String str = "Would you like to enable timer mode? Current progress will be lost.\n";
				int diaResult = JOptionPane.showConfirmDialog(this, str, "Warning", JOptionPane.YES_NO_OPTION);
				if (diaResult == JOptionPane.YES_OPTION) {
					resetTimer();
					timerPanel.setVisible(true);
					game.resetMap(currLevel);
					game.resetMovesMade();
					currLevel = 0;
					updateInterface(MODE_RESTART, game);
					requestFocus();
				} else {
					requestFocus();
				}
				timerOn = true;
			} else {
				timerPanel.setVisible(false);
				timerOn = false;
				requestFocus();
			}
		} else if (e.getSource() == WbMenu) {
			String str = "Are you sure you want to go to the menu?\n";
			int diaResult = JOptionPane.showConfirmDialog(this, str, "Menu", JOptionPane.YES_NO_OPTION);
			if(diaResult == JOptionPane.YES_OPTION) {
				setVisible(false);
				StartingScreen startMenu = new StartingScreen();
			} else {
				requestFocus();
			}
		} else if (e.getSource() == WbPre) {
			if(currLevel > 0) {
				currLevel--;
				updateInterface(MODE_REFRESH, game);
			}
			game.resetMovesMade();
			game.resetMap(currLevel);
			resetTimer();
			requestFocus();
		} else if (e.getSource() == WbNext) {
			if(game.hasNextLevel(currLevel)) {
				currLevel++;
				updateInterface(MODE_REFRESH, game);
			}
			game.resetMovesMade();
			game.resetMap(currLevel);
			resetTimer();
			requestFocus();
		} else if (e.getSource() == WbFirst) {
			currLevel = 0;
			updateInterface(MODE_REFRESH, game);
			game.resetMovesMade();
			game.resetMap(currLevel);
			requestFocus();
		} else if (e.getSource() == WbUndo) {
			game.undo(currLevel);
			updateInterface(MODE_REFRESH, game);
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
		} else if(e.getSource() == help) {
			//setVisible(false);
			HelpScreen hS = new HelpScreen();
			//requestFocus();
		}
	}
	
	public void resetTimer() {
		this.seconds1 = 0;
		this.seconds2 = 0;
		this.minutes1 = 0;
		this.minutes2 = 0;
		this.milliseconds = 0;
		this.secondsPlayed = 0;
	}

	// Main game interface
	public class MyPanel extends JPanel {
		int x = 20;
		int startx = 0;
		int starty = 0;

		
		Toolkit kit = Toolkit.getDefaultToolkit();
		ArrayList<ArrayList<String>> map;
		Image mapimg[] = { // Gets the image files from "img" folder
				kit.getImage("img/box1.png"), 
				kit.getImage("img/goal1.png"), 
				kit.getImage("img/ground1.png"),
				kit.getImage("img/player.png"), 
				kit.getImage("img/wall1.png"), 
				kit.getImage("img/goal-box1.png"),
				kit.getImage("img/player2.png")};
// More
		
																													// images
																													// can
																													// be
																													// added

		public MyPanel(ArrayList<ArrayList<String>> map) {
			setSize(startx*2+(12*x), starty*2 + (12*x));
			this.map = map;
			requestFocus();
		}

		public void paint(Graphics g) {
				ArrayList<ArrayList<String>> currMap = game.getLevel(currLevel).getMap(); // Get the map representing the current level.
				for (int i = 0; i < NUM_COLS; i++) {
					for (int j = 0; j < NUM_ROWS; j++) {
						String currString = currMap.get(j).get(i);
						if (currString.equals("B")) { // Box
							g.drawImage(mapimg[0], i * x + startx, j * x + starty, x, x, this);
						} else if (currString.equals("T")) { // goal square
							g.drawImage(mapimg[1], i * x + startx, j * x + starty, x, x, this);
						} else if (currString.equals("E")) { // empty
							g.drawImage(mapimg[2], i * x + startx, j * x + starty, x, x, this);
						} else if (currString.equals("P")||currString.equals("O")) { // player Probably not needed
							g.drawImage(mapimg[2], i * x + startx, j * x + starty, x, x, this);
							g.drawImage(mapimg[3], i * x + startx, j * x + starty, x, x, this);
						} else if (currString.equals("Q") || currString.equals("R")) { // player Probably not needed
							g.drawImage(mapimg[2], i * x + startx, j * x + starty, x, x, this);
							g.drawImage(mapimg[6], i * x + startx, j * x + starty, x, x, this);
						} else if (currString.equals("W")) { // wall
							g.drawImage(mapimg[4], i * x + startx, j * x + starty, x, x, this);
						} else if (currString.equals("D")) { // box is on goal
							g.drawImage(mapimg[5], i * x + startx, j * x + starty, x, x, this);
						}
					}
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
			game.resetMap(currLevel);
			game.resetMovesMade();
			//currLevel = 0; // Used for when the restart button is pressed
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
			
			//game.printMovesMade();
			game.resetMap(currLevel);
			game.resetMovesMade();
			
			if (game.hasNextLevel(currLevel)) { // AND the game has another
				//MiddleScreen mS = new MiddleScreen(game.getLevel(currLevel).getNumGoalBoxes(), secondsPlayed);		

				currLevel++; // THEN move to the next level and refresh the interface
				updateInterface(MODE_REFRESH, game);
				//boxesOnGoal, secondsPlayed
				resetTimer();
				
			} else { // ELSE finish the game
				//System.out.println("done.");
				//replace the current panel with a closing message
				//System.exit(0);
				updateInterface(MODE_DONE, game); // **YET TO BE IMPLEMENTED**F
				new EndingScreen();
				setVisible(false);
				resetTimer();
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
		if (e.getKeyCode()>=37 && e.getKeyCode()<=40){
			Animation.DIR = e.getKeyCode();
		}else if (e.getKeyCode() == 65||e.getKeyCode() == 68||e.getKeyCode() == 87||e.getKeyCode() == 83){
			Animation.DIR_Lu = e.getKeyCode();
		}
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
			case KeyEvent.VK_U:
				game.undo(currLevel);
				updateInterface(MODE_REFRESH, game);
				requestFocus();
				break;
			case KeyEvent.VK_N:
				//Next level
				if(game.hasNextLevel(currLevel)) {
					currLevel++;
				}
				updateInterface(MODE_REFRESH, game);
				game.resetMovesMade();
				game.resetMap(currLevel);
				resetTimer();
				requestFocus();
				break;
			case KeyEvent.VK_P:
				//Previous level
				if(currLevel > 0) {
					currLevel--;
				}
				updateInterface(MODE_REFRESH, game);
				game.resetMovesMade();
				game.resetMap(currLevel);
				resetTimer();
				requestFocus();
				break;
			case KeyEvent.VK_T:
				//Time
				if(!timerOn) {
					String str = "Would you like to enable timer mode? Current progress will be lost.\n";
					int diaResult = JOptionPane.showConfirmDialog(this, str, "Warning", JOptionPane.YES_NO_OPTION);
					if (diaResult == JOptionPane.YES_OPTION) {
						resetTimer();
						timerPanel.setVisible(true);
						game.resetMap(currLevel);
						game.resetMovesMade();
						currLevel = 0;
						updateInterface(MODE_RESTART, game);
						requestFocus();
					} else {
						requestFocus();
					}
					timerOn = true;
				} else {
					timerPanel.setVisible(false);
					timerOn = false;
					requestFocus();
				}
				break;
			case KeyEvent.VK_R:
				//Reset
				String str = "Would you like to restart this level?\n";
				int diaResult = JOptionPane.showConfirmDialog(this, str, "Warning", JOptionPane.YES_NO_OPTION);
				if (diaResult == JOptionPane.YES_OPTION) {
					updateInterface(MODE_RESTART, game);
					requestFocus();
				} else {
					requestFocus();
				}
				resetTimer();
				break;
			case KeyEvent.VK_M:
				//Music on/off
				requestFocus();
				break;
			}
			
		}
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		Animation.PRESSED = false;
	}

	public Game game;
	
	private Animation animation;

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	}
