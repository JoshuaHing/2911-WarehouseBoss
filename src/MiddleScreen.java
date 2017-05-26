import java.awt.BorderLayout;
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
import javax.swing.JOptionPane;

public class MiddleScreen extends JFrame implements ActionListener {
	JButton Close, continueGame, menu;

	int X = 1410;
	int Y = 200;
	int boxesOnGoal;
	int secondsPlayed;

	public MiddleScreen(int boxesOnGoal, int secondsPlayed) {
		this.boxesOnGoal = boxesOnGoal;
		this.secondsPlayed = secondsPlayed;
		setTitle("Warehouse Boss 2017-COMP2911");
		setSize(1678, 888);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("MenuImage/bg_congratulation menu.png")));
		setLayout(null);

	    Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("img/box.png");
		setIconImage(image);
	    second = new JButton("Close");
	    second.setFont(new Font("Arial", Font.BOLD, 25));
	    second.setHorizontalTextPosition(JButton.CENTER);
	    Close.setVerticalTextPosition(JButton.CENTER);
	    try {
	        Image img1 = ImageIO.read(getClass().getResource("MenuImage/GUI_05.png"));
	        Image img2 = ImageIO.read(getClass().getResource("MenuImage/GUI_06.png"));
	        second.setIcon(new ImageIcon(img1));
	        second.setDisabledIcon(new ImageIcon(img2));
	        second.setPressedIcon(new ImageIcon(img2));
	        second.setSelectedIcon(new ImageIcon(img2));
	        second.setDisabledSelectedIcon(new ImageIcon(img2));
	      } catch (Exception ex) {
	        System.out.println(ex);
	      }
	    second.setOpaque(false);
	    second.setContentAreaFilled(false);
	    second.setBorderPainted(false);
	    second.setFocusPainted(false);
	    second.setBounds(X - 277, Y + 280, 200, 92);
	    second.addActionListener(this);
	    add(second);
		setSize(1678, 888);


	    Close = new JButton("Close");
	    Close.setFont(new Font("Arial", Font.BOLD, 25));
	    Close.setHorizontalTextPosition(JButton.CENTER);
	    Close.setVerticalTextPosition(JButton.CENTER);
	    try {
	        Image img1 = ImageIO.read(getClass().getResource("MenuImage/GUI_05.png"));
	        Image img2 = ImageIO.read(getClass().getResource("MenuImage/GUI_06.png"));
	        Close.setIcon(new ImageIcon(img1));
	        Close.setDisabledIcon(new ImageIcon(img2));
	        Close.setPressedIcon(new ImageIcon(img2));
	        Close.setSelectedIcon(new ImageIcon(img2));
	        Close.setDisabledSelectedIcon(new ImageIcon(img2));
	      } catch (Exception ex) {
	        System.out.println(ex);
	      }
	    Close.setOpaque(false);
	    Close.setContentAreaFilled(false);
	    Close.setBorderPainted(false);
	    Close.setFocusPainted(false);
	    Close.setBounds(X - 277, Y + 280, 200, 92);
	    Close.addActionListener(this);
	    add(Close);
		setSize(1678, 888);
	}


	//if the game reaches the next level, I want to set the ending screen to be visible.
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == Close) {
			String str = "Are you sure you want to exit?\n";
			int diaResult = JOptionPane.showConfirmDialog(this, str, "Warning", JOptionPane.YES_NO_OPTION);
			if(diaResult == JOptionPane.YES_OPTION) {
				System.exit(0);
			} else {
				requestFocus();
			}
		} else if(e.getSource() == menu) {
			setVisible(false);
			StartingScreen startScreen = new StartingScreen();
		} else if(e.getSource() == continueGame) {
			setVisible(false);
			StartingScreen startScreen = new StartingScreen();
		}
	}
	

}

