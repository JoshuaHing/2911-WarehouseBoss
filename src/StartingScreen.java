import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class StartingScreen extends JFrame implements ActionListener
{
  JButton Exit, SPlayer, TPlayer, help;
  
  String mode;
  int X = 1410;
  int Y = 200;

  private static final int NUM_ROWS = 12;
  private static final int NUM_COLS = 12;

  public StartingScreen()
  {
    setTitle("Warehouse Boss 2017-COMP2911");
    setSize(1678,888);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);

    setLayout(new BorderLayout());
    setContentPane(new JLabel(new ImageIcon("MenuImage/bg_start menu.png")));
    setLayout(null);

    /*JLabel title = new JLabel("Warehouse Boss");
    title.setBounds(X-332, Y-85, 400, 100);
    title.setFont(new Font("Arial Black", Font.BOLD,30));
    add(title);*/
    
    Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image image = toolkit.getImage("img/box.png");
	setIconImage(image);
    //ImageIcon icon = new ImageIcon("MenuImage/gui_07.bmp");
    //JButton button = new JButton(icon);

   // SPlayer = new JButton(icon);

    //SPlayer.setFont(new Font("Arial", Font.PLAIN, 25));
    SPlayer = new JButton("Single Player");
    SPlayer.setFont(new Font("Arial", Font.BOLD, 25));
    SPlayer.setHorizontalTextPosition(JButton.CENTER);
    SPlayer.setVerticalTextPosition(JButton.CENTER);
    /*sPlayerText.setAlignmentX(X-280);
    sPlayerText.setAlignmentX(Y-80);
    sPlayerText.setVisible(true);*/
    try {
      Image img1 = ImageIO.read(getClass().getResource("MenuImage/GUI_01.png"));
      Image img2 = ImageIO.read(getClass().getResource("MenuImage/GUI_02.png"));
      SPlayer.setIcon(new ImageIcon(img1));
      SPlayer.setDisabledIcon(new ImageIcon(img2));
      SPlayer.setPressedIcon(new ImageIcon(img2));
      SPlayer.setSelectedIcon(new ImageIcon(img2));
      SPlayer.setDisabledSelectedIcon(new ImageIcon(img2));
    } catch (Exception ex) {
      System.out.println(ex);
    }
    SPlayer.setOpaque(false);
    SPlayer.setContentAreaFilled(false);
    SPlayer.setBorderPainted(false);
    SPlayer.setFocusPainted(false);
    SPlayer.setBounds(X-277, Y+50, 200, 92);
    //SPlayer.setIcon(new ImageIcon("MenuImage/gui_07.bmp"));
    SPlayer.addActionListener(this);
    add(SPlayer);

    TPlayer = new JButton("Two Player");
    TPlayer.setFont(new Font("Arial", Font.BOLD, 25));
    TPlayer.setHorizontalTextPosition(JButton.CENTER);
    TPlayer.setVerticalTextPosition(JButton.CENTER);
    /*sPlayerText.setAlignmentX(X-280);
    sPlayerText.setAlignmentX(Y-80);
    sPlayerText.setVisible(true);*/
    try {
        Image img1 = ImageIO.read(getClass().getResource("MenuImage/GUI_01.png"));
        Image img2 = ImageIO.read(getClass().getResource("MenuImage/GUI_02.png"));
        TPlayer.setIcon(new ImageIcon(img1));
        TPlayer.setDisabledIcon(new ImageIcon(img2));
        TPlayer.setPressedIcon(new ImageIcon(img2));
        TPlayer.setSelectedIcon(new ImageIcon(img2));
        TPlayer.setDisabledSelectedIcon(new ImageIcon(img2));
    } catch (Exception ex) {
      System.out.println(ex);
    }
    TPlayer.setOpaque(false);
    TPlayer.setContentAreaFilled(false);
    TPlayer.setBorderPainted(false);
    TPlayer.setFocusPainted(false);
    TPlayer.setBounds(X-277, Y+170, 200, 92);
    TPlayer.addActionListener(this);
    add(TPlayer);

    Exit = new JButton("Exit");
    Exit.setFont(new Font("Arial", Font.BOLD, 25));
    Exit.setHorizontalTextPosition(JButton.CENTER);
    Exit.setVerticalTextPosition(JButton.CENTER);
    try {
        Image img1 = ImageIO.read(getClass().getResource("MenuImage/GUI_01.png"));
        Image img2 = ImageIO.read(getClass().getResource("MenuImage/GUI_02.png"));
        Exit.setIcon(new ImageIcon(img1));
        Exit.setDisabledIcon(new ImageIcon(img2));
        Exit.setPressedIcon(new ImageIcon(img2));
        Exit.setSelectedIcon(new ImageIcon(img2));
        Exit.setDisabledSelectedIcon(new ImageIcon(img2));
      } catch (Exception ex) {
        System.out.println(ex);
      }
    Exit.setOpaque(false);
    Exit.setContentAreaFilled(false);
    Exit.setBorderPainted(false);
    Exit.setFocusPainted(false);
    Exit.setBounds(X-277, Y+290, 200, 92);
    Exit.addActionListener(this);
    add(Exit);

    help = new JButton();
    //help.setFont(new Font("Arial", Font.BOLD, 25));
    /*help.setHorizontalTextPosition(JButton.CENTER);
    help.setVerticalTextPosition(JButton.CENTER);*/
    try {
        Image img1 = ImageIO.read(getClass().getResource("MenuImage/button_05.png"));
        help.setIcon(new ImageIcon(img1));
      } catch (Exception ex) {
        System.out.println(ex);
      }
    help.setOpaque(false);
    help.setContentAreaFilled(false);
    help.setBorderPainted(false);
    help.setFocusPainted(false);
    help.setBounds(X-277, Y+470, 200, 92);
    help.addActionListener(this);
    add(help);

    
    setSize(1677,887);
    setSize(1678,888);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == Exit){
    	String str = "Are you sure you want to exit?\n";
		int diaResult = JOptionPane.showConfirmDialog(this, str, "Warning", JOptionPane.YES_NO_OPTION);
		if(diaResult == JOptionPane.YES_OPTION) {
			System.exit(0);
		} else {
			requestFocus();
		}
    }else if (e.getSource() == SPlayer){
    	mode = "Single Player";
    	setVisible(false);
    	DifficultyScreen dS = new DifficultyScreen(mode);
    }else if (e.getSource() == TPlayer) {
    	mode = "Multi Player";
    	setVisible(false);
    	DifficultyScreen dS = new DifficultyScreen(mode);
    }else if (e.getSource() == help) {
    	HelpScreen hS = new HelpScreen();
    }

  }
	
	
}
