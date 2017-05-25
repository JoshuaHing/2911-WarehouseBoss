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
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class StartingScreen extends JFrame implements ActionListener
{
  JButton Exit, SPlayer, TPlayer;

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
      Image img = ImageIO.read(getClass().getResource("MenuImage/icon.png"));
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
      Image img = ImageIO.read(getClass().getResource("MenuImage/icon.png"));
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
        Image img = ImageIO.read(getClass().getResource("MenuImage/icon.png"));
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
      readMap("SinglePlayer");
    }else if (e.getSource() == TPlayer) {
      readMap("MultiPlayer");
    }
  }

  public void readMap(String mode){
    ArrayList<ArrayList<String>> map = null;
    // ****************SCANNER STARTS****************
    Scanner sc = null;
    int numGoals = 0;
    Game game = new Game();
    try { 														// We need to keep going and take in all the maps
      sc = new Scanner(new FileReader("map/"+ mode + ".txt"));
      while (sc.hasNextLine()) {
        while (sc.hasNext("#")) {
          sc.nextLine();
        }
        //Let's go through the elements
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
      //game.setInitialMap(game.getLevel(0).getMap());
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