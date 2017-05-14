//import WarehouseBossInterface.STATE;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

  @Override
  public void mouseClicked(MouseEvent e) {

  }

  public void mousePressed(MouseEvent e) {
    /*public Rectangle playButton  =
        new Rectangle(WarehouseBossInterface.WIDTH / 2 +120, 150, 200,50);
    public Rectangle MultiPlayer  =
        new Rectangle(WarehouseBossInterface.WIDTH / 2 +120, 250, 200,50);
    public Rectangle quitButton  =
        new Rectangle(WarehouseBossInterface.WIDTH / 2 +120, 350, 200,50);*/


    int mx = e.getX();
    System.out.println("x is " + mx);
    int my = e.getY();
    System.out.println("y is " + my);
    if (mx >= 270 && mx <= 470){
      if (my >= 320 && my <= 370){
        //Pressed new game Button
        System.out.println(WarehouseBossInterface.state);
        WarehouseBossInterface.state = WarehouseBossInterface.STATE.GAME;
        System.out.println(WarehouseBossInterface.state);
      }else {
        System.out.println(WarehouseBossInterface.state);
      }
    }
  }


  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
