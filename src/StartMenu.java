import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class StartMenu {
  public Rectangle playButton  =
      new Rectangle(WarehouseBossInterface.WIDTH / 2 +120, 150, 200,50);
  public Rectangle MultiPlayer  =
      new Rectangle(WarehouseBossInterface.WIDTH / 2 +120, 250, 200,50);
  public Rectangle quitButton  =
      new Rectangle(WarehouseBossInterface.WIDTH / 2 +120, 350, 200,50);

  public void render (Graphics g){
    Graphics2D g2d = (Graphics2D) g;

    Font fnt0 = new Font("arial", Font.BOLD, 40);
    g.setFont(fnt0);
    g.setColor(Color.black);
    g.drawString("      Warehouse Boss", WarehouseBossInterface.WIDTH/2, 60);

    Font fnt1 = new Font("arial", Font.BOLD, 30);
    g.setFont(fnt1);
    g.setColor(Color.black);
    g.drawString(" New Game", playButton.x + 19, playButton.y+30);
    g2d.draw(playButton);

    Font fnt2 = new Font("arial", Font.BOLD, 30);
    g.setFont(fnt2);
    g.setColor(Color.black);
    g.drawString("Multi-Player", MultiPlayer.x + 19, MultiPlayer.y+30);
    g2d.draw(MultiPlayer);

    Font fnt3 = new Font("arial", Font.BOLD, 30);
    g.setFont(fnt3);
    g.setColor(Color.black);
    g.drawString("      Quit", quitButton.x + 19, quitButton.y+30);
    g2d.draw(quitButton);
  }

}
