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


public class StartingScreen extends JFrame implements ActionListener
{
  JButton Exit, SPlayer, TPlayer;

  int X = 1410;
  int Y = 200;

  private static final int NUM_ROWS = 10;
  private static final int NUM_COLS = 10;

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

    JLabel title = new JLabel("Ware House Boss");
    title.setBounds(X-250, Y-80, 200, 92);
    title.setFont(new Font("Arial Black", Font.BOLD,20));
    add(title);

    SPlayer = new JButton("Single Player");
    SPlayer.setBounds(X-250, Y+80, 200, 92);
    SPlayer.setIcon(new ImageIcon("MenuImage/gui_07.bmp"));
    SPlayer.addActionListener(this);
    add(SPlayer);

    TPlayer = new JButton("Two Player");
    TPlayer.setBounds(X-250, Y+250, 200, 92);
    TPlayer.setIcon(new ImageIcon("MenuImage/gui_07.bmp"));
    TPlayer.addActionListener(this);
    add(TPlayer);

    Exit = new JButton("Exit");
    Exit.setBounds(X-250, Y+420, 200, 92);
    Exit.setIcon(new ImageIcon("MenuImage/gui_07.bmp"));
    Exit.addActionListener(this);
    add(Exit);

    // Just for refresh :) Not optional!
    setSize(1677,887);
    setSize(1678,888);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == Exit){
      System.exit(0);
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
    int numRows = 0;
    int numCols = 0;
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