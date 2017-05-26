import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DifficultyScreen extends JFrame implements ActionListener {
	JButton hardButton, easyButton, medButton, menuButton;

	int X = 1410;
	int Y = 200;
	
	String gameMode;

	public DifficultyScreen(String mode) {
		setTitle("Warehouse Boss 2017-COMP2911");
		setSize(1678, 888);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("MenuImage/bg_selection menu.png")));
		setLayout(null);
		
	    Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("img/box.png");
		setIconImage(image);


		// ImageIcon icon = new ImageIcon("MenuImage/gui_07.bmp");
		// JButton button = new JButton(icon);

		// easyButton = new JButton(icon);
		gameMode = mode;

		// easyButton.setFont(new Font("Arial", Font.PLAIN, 25));
		easyButton = new JButton("Easy");
		easyButton.setFont(new Font("Arial", Font.BOLD, 25));
		easyButton.setHorizontalTextPosition(JButton.CENTER);
		easyButton.setVerticalTextPosition(JButton.CENTER);
		/*
		 * easyButtonText.setAlignmentX(X-280);
		 * easyButtonText.setAlignmentX(Y-80); easyButtonText.setVisible(true);
		 */
		try {
	        Image img1 = ImageIO.read(getClass().getResource("MenuImage/GUI_03.png"));
	        Image img2 = ImageIO.read(getClass().getResource("MenuImage/GUI_04.png"));
	        easyButton.setIcon(new ImageIcon(img1));
	        easyButton.setDisabledIcon(new ImageIcon(img2));
	        easyButton.setPressedIcon(new ImageIcon(img2));
	        easyButton.setSelectedIcon(new ImageIcon(img2));
	        easyButton.setDisabledSelectedIcon(new ImageIcon(img2));
		} catch (Exception ex) {
			//System.out.println(ex);
		}
		easyButton.setOpaque(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setBorderPainted(false);
		easyButton.setFocusPainted(false);
		easyButton.setBounds(X - 277, Y + 50, 200, 92);
		// easyButton.setIcon(new ImageIcon("MenuImage/gui_07.bmp"));
		easyButton.addActionListener(this);
		add(easyButton);

		medButton = new JButton("Medium");
		medButton.setFont(new Font("Arial", Font.BOLD, 25));
		medButton.setHorizontalTextPosition(JButton.CENTER);
		medButton.setVerticalTextPosition(JButton.CENTER);
		/*
		 * easyButtonText.setAlignmentX(X-280);
		 * easyButtonText.setAlignmentX(Y-80); easyButtonText.setVisible(true);
		 */
		try {
	        Image img1 = ImageIO.read(getClass().getResource("MenuImage/GUI_03.png"));
	        Image img2 = ImageIO.read(getClass().getResource("MenuImage/GUI_04.png"));
	        medButton.setIcon(new ImageIcon(img1));
	        medButton.setDisabledIcon(new ImageIcon(img2));
	        medButton.setPressedIcon(new ImageIcon(img2));
	        medButton.setSelectedIcon(new ImageIcon(img2));
	        medButton.setDisabledSelectedIcon(new ImageIcon(img2));
		} catch (Exception ex) {
			//System.out.println(ex);
		}
		medButton.setOpaque(false);
		medButton.setContentAreaFilled(false);
		medButton.setBorderPainted(false);
		medButton.setFocusPainted(false);
		medButton.setBounds(X - 277, Y + 170, 200, 92);
		medButton.addActionListener(this);
		add(medButton);

		hardButton = new JButton("Hard");
		hardButton.setFont(new Font("Arial", Font.BOLD, 25));
		hardButton.setHorizontalTextPosition(JButton.CENTER);
		hardButton.setVerticalTextPosition(JButton.CENTER);
		try {
	        Image img1 = ImageIO.read(getClass().getResource("MenuImage/GUI_03.png"));
	        Image img2 = ImageIO.read(getClass().getResource("MenuImage/GUI_04.png"));
	        hardButton.setIcon(new ImageIcon(img1));
	        hardButton.setDisabledIcon(new ImageIcon(img2));
	        hardButton.setPressedIcon(new ImageIcon(img2));
	        hardButton.setSelectedIcon(new ImageIcon(img2));
	        hardButton.setDisabledSelectedIcon(new ImageIcon(img2));
		} catch (Exception ex) {
			//System.out.println(ex);
		}
		hardButton.setOpaque(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setBorderPainted(false);
		hardButton.setFocusPainted(false);
		hardButton.setBounds(X - 277, Y + 290, 200, 92);
		hardButton.addActionListener(this);
		add(hardButton);

		menuButton = new JButton("Back");
		menuButton.setFont(new Font("Arial", Font.BOLD, 25));
		menuButton.setHorizontalTextPosition(JButton.CENTER);
		menuButton.setVerticalTextPosition(JButton.CENTER);
		/*
		 * easyButtonText.setAlignmentX(X-280);
		 * easyButtonText.setAlignmentX(Y-80); easyButtonText.setVisible(true);
		 */
		try {
	        Image img1 = ImageIO.read(getClass().getResource("MenuImage/GUI_03.png"));
	        Image img2 = ImageIO.read(getClass().getResource("MenuImage/GUI_04.png"));
	        menuButton.setIcon(new ImageIcon(img1));
	        menuButton.setDisabledIcon(new ImageIcon(img2));
	        menuButton.setPressedIcon(new ImageIcon(img2));
	        menuButton.setSelectedIcon(new ImageIcon(img2));
	        menuButton.setDisabledSelectedIcon(new ImageIcon(img2));
		} catch (Exception ex) {
			//System.out.println(ex);
		}
		menuButton.setOpaque(false);
		menuButton.setContentAreaFilled(false);
		menuButton.setBorderPainted(false);
		menuButton.setFocusPainted(false);
		menuButton.setBounds(X - 277, Y + 470, 200, 92);
		// easyButton.setIcon(new ImageIcon("MenuImage/gui_07.bmp"));
		menuButton.addActionListener(this);
		add(menuButton);
		// Just for refresh :) Not optional!
		setSize(1677, 887);
		setSize(1678, 888);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == easyButton) {
			readMap("Easy");
			Animation.PRESSED = false;
		} else if (e.getSource() == medButton) {
			readMap("Medium");
			Animation.PRESSED = false;
		} else if (e.getSource() == hardButton) {
			readMap("Hard");
			Animation.PRESSED = false;
		} else if(e.getSource() == menuButton) {
			setVisible(false);
			StartingScreen sS = new StartingScreen();
		}
	}

	public void readMap(String mode) {
		TempGenerator tG;
		if(mode.equals("Easy")) {	
			tG = new TempGenerator(3, this.gameMode);
			Game game = tG.getGame(3, this.gameMode);
			game.setMap(game.getLevel(0));
			setVisible(false);
			WarehouseBossInterface newInterface = new WarehouseBossInterface(game);
			EventQueue.invokeLater(() -> newInterface.canvas.start());
		} else if(mode.equals("Medium")) {
			tG = new TempGenerator(6, this.gameMode);
			Game game = tG.getGame(6, this.gameMode);
			game.setMap(game.getLevel(0));
			setVisible(false);
			WarehouseBossInterface newInterface = new WarehouseBossInterface(game);
			EventQueue.invokeLater(() -> newInterface.canvas.start());
		} else if(mode.equals("Hard")) {
			tG = new TempGenerator(8, this.gameMode);
			Game game = tG.getGame(8, this.gameMode);
			game.setMap(game.getLevel(0));
			setVisible(false);
			WarehouseBossInterface newInterface = new WarehouseBossInterface(game);
			EventQueue.invokeLater(() -> newInterface.canvas.start());
		}
	}
}
