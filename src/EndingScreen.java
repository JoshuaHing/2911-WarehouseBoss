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

public class EndingScreen extends JFrame implements ActionListener {
	JButton quit, continueGame, menu;

	int X = 1410;
	int Y = 200;

	public EndingScreen() {
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

	    menu = new JButton("Main Menu");
	    menu.setFont(new Font("Arial", Font.BOLD, 25));
	    menu.setHorizontalTextPosition(JButton.CENTER);
	    menu.setVerticalTextPosition(JButton.CENTER);
	    /*sPlayerText.setAlignmentX(X-280);
	    sPlayerText.setAlignmentX(Y-80);
	    sPlayerText.setVisible(true);*/
	    try {
	        Image img1 = ImageIO.read(getClass().getResource("MenuImage/GUI_05.png"));
	        Image img2 = ImageIO.read(getClass().getResource("MenuImage/GUI_06.png"));
	        menu.setIcon(new ImageIcon(img1));
	        menu.setDisabledIcon(new ImageIcon(img2));
	        menu.setPressedIcon(new ImageIcon(img2));
	        menu.setSelectedIcon(new ImageIcon(img2));
	        menu.setDisabledSelectedIcon(new ImageIcon(img2));
	    } catch (Exception ex) {
	      //System.out.println(ex);
	    }
	    menu.setOpaque(false);
	    menu.setContentAreaFilled(false);
	    menu.setBorderPainted(false);
	    menu.setFocusPainted(false);	
	    menu.setBounds(X - 277, Y + 140, 200, 92);
	    menu.addActionListener(this);
	    add(menu);

	    quit = new JButton("quit");
	    quit.setFont(new Font("Arial", Font.BOLD, 25));
	    quit.setHorizontalTextPosition(JButton.CENTER);
	    quit.setVerticalTextPosition(JButton.CENTER);
	    try {
	        Image img1 = ImageIO.read(getClass().getResource("MenuImage/GUI_05.png"));
	        Image img2 = ImageIO.read(getClass().getResource("MenuImage/GUI_06.png"));
	        quit.setIcon(new ImageIcon(img1));
	        quit.setDisabledIcon(new ImageIcon(img2));
	        quit.setPressedIcon(new ImageIcon(img2));
	        quit.setSelectedIcon(new ImageIcon(img2));
	        quit.setDisabledSelectedIcon(new ImageIcon(img2));
	      } catch (Exception ex) {
	       // System.out.println(ex);
	      }
	    quit.setOpaque(false);
	    quit.setContentAreaFilled(false);
	    quit.setBorderPainted(false);
	    quit.setFocusPainted(false);
	    quit.setBounds(X - 277, Y + 280, 200, 92);
	    quit.addActionListener(this);
	    add(quit);
		setSize(1678, 888);
	}


	//if the game reaches the next level, I want to set the ending screen to be visible.
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == quit) {
			String str = "Are you sure you want to exit?\n";
			int diaResult = JOptionPane.showConfirmDialog(this, str, "Warning", JOptionPane.YES_NO_OPTION);
			if(diaResult == JOptionPane.YES_OPTION) {
				System.exit(0);
			} else {
				requestFocus();
			}
		} else if(e.getSource() == menu) {
			dispose();
			//setVisible(false);
			StartingScreen startScreen = new StartingScreen();
		} else if(e.getSource() == continueGame) {
			dispose();
			//setVisible(false);
			StartingScreen startScreen = new StartingScreen();
		}
	}
	

}
