import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import javax.imageio.*;

public class EndingScreen extends JFrame implements ActionListener {
	JButton Quit, continueGame, mainMenu;
	StartingScreen startScreen;

	int X = 1410;
	int Y = 200;
	private boolean finished = false;

	public EndingScreen() {
		startScreen = new StartingScreen();
		setTitle("Warehouse Boss 2017-COMP2911");
		setSize(1678, 888);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(false);

		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("MenuImage/bg_start menu.png")));
		setLayout(null);

		JLabel title = new JLabel("Ware House Boss");
		title.setBounds(X - 250, Y - 80, 200, 92);
		title.setFont(new Font("Arial Black", Font.BOLD, 20));
		add(title);

		continueGame = new JButton("continueGame");
		continueGame.setBounds(X - 250, Y + 80, 200, 92);
		continueGame.setIcon(new ImageIcon("MenuImage/gui_07.bmp"));
		continueGame.addActionListener(this);
		add(continueGame);

		mainMenu = new JButton("Main Menu");
		mainMenu.setBounds(X - 250, Y + 250, 200, 92);
		mainMenu.setIcon(new ImageIcon("MenuImage/gui_07.bmp"));
		mainMenu.addActionListener(this);
		add(mainMenu);

		Quit = new JButton("Quit");
		Quit.setBounds(X - 250, Y + 420, 200, 92);
		Quit.setIcon(new ImageIcon("MenuImage/gui_07.bmp"));
		Quit.addActionListener(this);
		add(Quit);
		setSize(1678, 888);
	}
	//if the game reaches the next level, I want to set the ending screen to be visible.
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == Quit) {
			String str = "Would you like to quit the game?.\n";
			int diaResult = JOptionPane.showConfirmDialog(this, str, "Warning", JOptionPane.YES_NO_OPTION);
			if(diaResult == JOptionPane.YES_OPTION) {
				System.exit(0);
			} else {
				requestFocus();
			}
		} else if(e.getSource() == mainMenu) {
			startScreen = new StartingScreen();
			setVisible(false);
		} else if(e.getSource() == continueGame) {
			startScreen.turnOn();
			setVisible(false);
		}
	}
	
	public void turnOn() {
		setVisible(true);
	}

}
