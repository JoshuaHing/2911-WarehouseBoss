import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class StartingScreen extends JFrame implements ActionListener
{
  JButton Exit, SPlayer, TPlayer;
  
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

    JLabel title = new JLabel("Warehouse Boss");
    title.setBounds(X-332, Y-85, 400, 100);
    title.setFont(new Font("Arial Black", Font.BOLD,30));
    add(title);
    
    //ImageIcon icon = new ImageIcon("MenuImage/gui_07.bmp");
    //JButton button = new JButton(icon);

   // SPlayer = new JButton(icon);
    

    //SPlayer.setFont(new Font("Arial", Font.PLAIN, 25));
    SPlayer = new JButton("Single Player");
    SPlayer.setFont(new Font("Arial", Font.PLAIN, 25));
    SPlayer.setHorizontalTextPosition(JButton.CENTER);
    SPlayer.setVerticalTextPosition(JButton.CENTER);
    /*sPlayerText.setAlignmentX(X-280);
    sPlayerText.setAlignmentX(Y-80);
    sPlayerText.setVisible(true);*/
    try {
      Image img = ImageIO.read(getClass().getResource("MenuImage/icon2.png"));
      SPlayer.setIcon(new ImageIcon(img));
    } catch (Exception ex) {
      System.out.println(ex);
    }
    SPlayer.setOpaque(false);
    SPlayer.setContentAreaFilled(false);
    SPlayer.setBorderPainted(false);
    SPlayer.setFocusPainted(false);
    SPlayer.setBounds(X-277, Y+80, 200, 92);
    //SPlayer.setIcon(new ImageIcon("MenuImage/gui_07.bmp"));
    SPlayer.addActionListener(this);
    add(SPlayer);

    TPlayer = new JButton("Two Player");
    TPlayer.setFont(new Font("Arial", Font.PLAIN, 25));
    TPlayer.setHorizontalTextPosition(JButton.CENTER);
    TPlayer.setVerticalTextPosition(JButton.CENTER);
    /*sPlayerText.setAlignmentX(X-280);
    sPlayerText.setAlignmentX(Y-80);
    sPlayerText.setVisible(true);*/
    try {
      Image img = ImageIO.read(getClass().getResource("MenuImage/icon2.png"));
      TPlayer.setIcon(new ImageIcon(img));
    } catch (Exception ex) {
      System.out.println(ex);
    }
    TPlayer.setOpaque(false);
    TPlayer.setContentAreaFilled(false);
    TPlayer.setBorderPainted(false);
    TPlayer.setFocusPainted(false);
    TPlayer.setBounds(X-277, Y+250, 200, 92);
    TPlayer.addActionListener(this);
    add(TPlayer);

    Exit = new JButton("Exit");
    Exit.setFont(new Font("Arial", Font.PLAIN, 25));
    Exit.setHorizontalTextPosition(JButton.CENTER);
    Exit.setVerticalTextPosition(JButton.CENTER);
    try {
        Image img = ImageIO.read(getClass().getResource("MenuImage/icon2.png"));
        Exit.setIcon(new ImageIcon(img));
      } catch (Exception ex) {
        System.out.println(ex);
      }
    Exit.setOpaque(false);
    Exit.setContentAreaFilled(false);
    Exit.setBorderPainted(false);
    Exit.setFocusPainted(false);
    Exit.setBounds(X-277, Y+420, 200, 92);
    Exit.addActionListener(this);
    add(Exit);

    // Just for refresh :) Not optional!
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
    }
  }

  

}