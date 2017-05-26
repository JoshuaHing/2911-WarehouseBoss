import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DifficultyScreen extends JFrame implements ActionListener {
	JButton hardButton, easyButton, medButton;

	int X = 1410;
	int Y = 200;

	private static final int NUM_ROWS = 12;
	private static final int NUM_COLS = 12;
	String gameMode;

	public DifficultyScreen(String mode) {
		setTitle("Warehouse Boss 2017-COMP2911");
		setSize(1678, 888);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("MenuImage/bg_start menu.png")));
		setLayout(null);

		JLabel title = new JLabel("Select Difficulty");
		title.setBounds(X - 332, Y - 85, 400, 100);
		title.setFont(new Font("Arial Black", Font.BOLD, 30));
		add(title);

		// ImageIcon icon = new ImageIcon("MenuImage/gui_07.bmp");
		// JButton button = new JButton(icon);

		// easyButton = new JButton(icon);
		gameMode = mode;

		// easyButton.setFont(new Font("Arial", Font.PLAIN, 25));
		easyButton = new JButton("Easy");
		easyButton.setFont(new Font("Arial", Font.PLAIN, 25));
		easyButton.setHorizontalTextPosition(JButton.CENTER);
		easyButton.setVerticalTextPosition(JButton.CENTER);
		/*
		 * easyButtonText.setAlignmentX(X-280);
		 * easyButtonText.setAlignmentX(Y-80); easyButtonText.setVisible(true);
		 */
		try {
			Image img = ImageIO.read(getClass().getResource("MenuImage/icon3.png"));
			easyButton.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		easyButton.setOpaque(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setBorderPainted(false);
		easyButton.setFocusPainted(false);
		easyButton.setBounds(X - 277, Y + 80, 200, 92);
		// easyButton.setIcon(new ImageIcon("MenuImage/gui_07.bmp"));
		easyButton.addActionListener(this);
		add(easyButton);

		medButton = new JButton("Medium");
		medButton.setFont(new Font("Arial", Font.PLAIN, 25));
		medButton.setHorizontalTextPosition(JButton.CENTER);
		medButton.setVerticalTextPosition(JButton.CENTER);
		/*
		 * easyButtonText.setAlignmentX(X-280);
		 * easyButtonText.setAlignmentX(Y-80); easyButtonText.setVisible(true);
		 */
		try {
			Image img = ImageIO.read(getClass().getResource("MenuImage/icon3.png"));
			medButton.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		medButton.setOpaque(false);
		medButton.setContentAreaFilled(false);
		medButton.setBorderPainted(false);
		medButton.setFocusPainted(false);
		medButton.setBounds(X - 277, Y + 250, 200, 92);
		medButton.addActionListener(this);
		add(medButton);

		hardButton = new JButton("Hard");
		hardButton.setFont(new Font("Arial", Font.PLAIN, 25));
		hardButton.setHorizontalTextPosition(JButton.CENTER);
		hardButton.setVerticalTextPosition(JButton.CENTER);
		try {
			Image img = ImageIO.read(getClass().getResource("MenuImage/icon3.png"));
			hardButton.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		hardButton.setOpaque(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setBorderPainted(false);
		hardButton.setFocusPainted(false);
		hardButton.setBounds(X - 277, Y + 420, 200, 92);
		hardButton.addActionListener(this);
		add(hardButton);

		// Just for refresh :) Not optional!
		setSize(1677, 887);
		setSize(1678, 888);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == easyButton) {
			readMap("Easy");
		} else if (e.getSource() == medButton) {
			readMap("Medium");
		} else if (e.getSource() == hardButton) {
			readMap("Hard");
		}
	}

	public void readMap(String mode) {
		ArrayList<ArrayList<String>> map = null;
		// ****************SCANNER STARTS****************
		Scanner sc = null;
		int numGoals = 0;
		Game game = new Game();
		try { // We need to keep going and take in all the maps
			if(gameMode.equals("Single Player")) {
				sc = new Scanner(new FileReader("map/SinglePlayer/" + mode + ".txt"));
			} else if(gameMode.equals("Multi Player")) {
				sc = new Scanner(new FileReader("map/MultiPlayer/" + mode + ".txt"));
			}
			while (sc.hasNextLine()) {
				while (sc.hasNext("#")) {
					sc.nextLine();
				}
				// Let's go through the elements
				if (sc.hasNextLine()) {
					map = new ArrayList<ArrayList<String>>();
					for (int i = 0; i < NUM_COLS; i++) {
						ArrayList<String> newList = new ArrayList<String>();
						map.add(newList);
						for (int j = 0; j < NUM_ROWS; j++) {
							if (sc.hasNext("P") || sc.hasNext("B") || sc.hasNext("T") || sc.hasNext("W")
									|| sc.hasNext("E") || sc.hasNext("O") || sc.hasNext("D") || sc.hasNext("Q")) {
								if (sc.hasNext("T")) {
									numGoals++;
								}
								map.get(i).add(sc.next());
							}
						}
					}

					// System.out.println("numGoals = " + numGoals);
					Map newMap = new Map(map, numGoals);
					game.addMap(newMap);
					numGoals = 0;
					if (sc.hasNextLine()) {
						sc.nextLine();
					}
				}
			}
			// System.out.println("map size = " + game.numMaps());
			// game.setInitialMap(game.getLevel(0).getMap());
			game.setMap(game.getLevel(0));
			setVisible(false);
			WarehouseBossInterface newInterface = new WarehouseBossInterface(game);
			EventQueue.invokeLater(() -> newInterface.canvas.start());
		} catch (FileNotFoundException f) {
		} finally {
			if (sc != null)
				sc.close();
		}
	}
}
