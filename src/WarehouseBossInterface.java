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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

	// Create MyPanel
	MyPanel mainPanel;
	JLabel label;
	boolean leftPressed;
	boolean rightPressed;
	boolean upPressed;
	boolean downPressed;

	public WarehouseBossInterface() {
		super("Warehouse Boss 2017-COMP2911");

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
		// end of code for arrow keys

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

		mainPanel = new MyPanel();
		mainPanel.setBounds(0, 60, 600, 600);
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
			// String str = "Do You Really Want to Exit?\n";
			// JOptionPane.showMessageDialog(this, str,"Warnnig",
			// JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}

	// Main game interface
	class MyPanel extends JPanel {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image mapimg[] = {

				kit.getImage("img/box.png"), kit.getImage("img/goal.png"), kit.getImage("img/goal-box.png"),
				kit.getImage("img/ground.png"), kit.getImage("img/out-of-bounds.png"), kit.getImage("img/player.png"),
				kit.getImage("img/wall.png"), };

		public MyPanel() {
			setSize(640, 640);
			requestFocus();
		}

		@Override
		public void paint(Graphics g) {
			for (int i = 0; i < 20; i++) {
				for (int j = 0; j < 20; j++) {
					g.drawImage(mapimg[i * j % 2], i * 32, j * 32, 32, 32, this);
				}
			}
		}
	}

	public void left() {
		System.out.println("move left");
	}

	public void right() {
		System.out.println("move right");
	}

	public void up() {
		System.out.println("move up");
	}

	public void down() {
		System.out.println("move down");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// debug key type System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());

		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
		switch(e.getKeyCode()){
			case KeyEvent.VK_LEFT:
				left();
				break;
			case KeyEvent.VK_RIGHT:
				right();
				break;
			case KeyEvent.VK_UP:
				up();
				break;
			case KeyEvent.VK_DOWN:
				down();
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}
