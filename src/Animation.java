
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Animation extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static boolean PRESSED = false;
	public static int goalX;				//the pixel need to move to 
	public static int goalY;
	public static int pointX = 280;				//current pixel
	public static int pointY = 100	;
	public static int RIGHT_Ma = 0;				//x-position of sub-image 
	public static int LEFT_Ma = 0;				//y-position of sub-image 
	public static int DIR = 0; 					// moving direction

	public static int goalX_Lu;					//the pixel need to move to 
	public static int goalY_Lu;
	public static int pointX_Lu = 281;				//current pixel
	public static int pointY_Lu = 100	;
	public static int RIGHT_Lu = 0;				//x-position of sub-image 
	public static int LEFT_Lu = 0;				//y-position of sub-image 
	public static int DIR_Lu = 0; 					// moving direction
	
	public static int ANGLE = 0;				//rotate or not
	public static int W = 80;					//box & wall dimension
	public static int M = 60;					//sub-picture dimension
	public static int H = 75;
	public static int X = (W/2);				//sub-picture dimension
	public static int Y = (W/4);				//sub-picture dimension

	public static int S = 4;					//speed
	public static long sleepTime = 30;			//delay
	
	public static int startX = 687;
	public static int startY = 177;

	public static int an = 0;
	public static int an_dir = 1;

	public static int bn = 0;
	public static int bn_dir = 1;

	
	static Random randomGenerator = new Random();


	@SuppressWarnings("serial")
	public static class pic extends Canvas implements Runnable {
		public BufferedImage Mario = null;
		public BufferedImage Luigi = null;

		public Image Box = null;
		public Image goalsquare = null;
		public Image empty = null;
		public Image player = null;
		public Image wall = null;		
		public Image boxongoal = null;
		
		public Image bg = null;
		public Image cl = null;
		public Image bu = null;
		public Image go = null;
		public Image b = null;


		
		public Image oneFPS = null;
		public Thread thread = null;
		
		ArrayList<ArrayList<String>> map;
		private Game game;
		
		public pic(ArrayList<ArrayList<String>> map, Game game) {
			this.map = map;
			this.game = game;
			try {
				Mario = ImageIO.read(new File("img/mario.png"));
				Luigi = ImageIO.read(new File("img/luigi.png"));
				Box = ImageIO.read(new File("img/box.png"));
				wall = ImageIO.read(new File ("img/wall01.png"));
				goalsquare = ImageIO.read(new File("img/goal.png"));
				empty = ImageIO.read(new File("img/grass01.png"));
				player = ImageIO.read(new File("img/player.png"));

				boxongoal = ImageIO.read(new File("img/goal-box.png"));
				
				bg = ImageIO.read(new File("img/bg.png"));
				cl = ImageIO.read(new File("img/cl.png"));
				bu = ImageIO.read(new File("img/bush.png"));
				go = ImageIO.read(new File("img/go.png"));
				b = ImageIO.read(new File("img/1.png"));

			} catch (IOException e) {
			}
			requestFocus();
		}

		@Override
		public void paint(Graphics g) 																		// Paints this canvas.
		{			
			Graphics2D g2d = (Graphics2D) g;

//			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

			g2d.drawImage(bg, 0, 0, 1675, 865, this);                  ///backgound size

			
			int i = 0;
			
			int j = 0;
			
			int c = 0;
			
			int e = 0;
					

			
			for (i = 0; i < 10; i++){
				for(j = 0; j < 10; j++){
					g2d.drawImage(empty, (startX+(i * X)-(j * X)), startY + (i+j)*Y, W, (W/20*31), this);
				}
			}
			
				ArrayList<ArrayList<String>> currMap = game.getLevel(WarehouseBossInterface.currLevel).getMap();
				for (c = 0; c < 19; c++) {
					if (c < 10){
						e = c;
					}else{
						e = 9;
					}
					
					for (j = (c - e); j < (e + 1); j++) {
						i = c - j;
						String currString = currMap.get(j).get(i);
						if (currString.equals("B")) { 										// Box
							g2d.drawImage(Box, (startX+(i * X)-(j * X)), startY + (i+j)*Y, W, W, this);
						} else if (currString.equals("T")) { 								// goal square
							g2d.drawImage(goalsquare, (startX+(i * X)-(j * X)), startY + (i+j)*Y, W, W, this);
						} else if (currString.equals("P")) {								// Mario
							goalX = (startX+(i * X)-(j * X));
							goalY = startY + (i+j)*Y;			
							if (RIGHT_Lu == (3 * M))
								RIGHT_Lu = 0;
							
							if (RIGHT_Ma == (3 * M))
								RIGHT_Ma = 0;
							g2d.drawImage(Mario.getSubimage(RIGHT_Ma, LEFT_Ma, M, H), pointX+10, (pointY-7), M, H, this);
						} else if (currString.equals("Q")) {								//Luigi
							goalX_Lu = (startX+(i * X)-(j * X));
							goalY_Lu = startY + (i+j)*Y;			
							g2d.drawImage(Luigi.getSubimage(RIGHT_Lu, LEFT_Lu, M, H), pointX_Lu+10, (pointY_Lu-7), M, H, this);
						} else if (currString.equals("O")) {								//Mario on goal
							g2d.drawImage(goalsquare, (startX+(i * X)-(j * X)), startY + (i+j)*Y, W, W, this);
							goalX = (startX+(i * X)-(j * X));
							goalY = startY + (i+j)*Y;			
							g2d.drawImage(Mario.getSubimage(RIGHT_Ma, LEFT_Ma, M, H), pointX +10, (pointY-7), M, H, this);

						} else if (currString.equals("R")) {								//Luigi on goal
							g2d.drawImage(goalsquare, (startX+(i * X)-(j * X)), startY + (i+j)*Y, W, W, this);
							goalX_Lu = (startX+(i * X)-(j * X));
							goalY_Lu =startY + (i+j)*Y;			
							g2d.drawImage(Luigi.getSubimage(RIGHT_Lu, LEFT_Lu, M, H), pointX_Lu+10,(pointY_Lu-7), M, H, this);

						} else if (currString.equals("W")) { 								// wall
							g2d.drawImage(wall, (startX+(i * X)-(j * X)), startY + (i+j)*Y, W, W, this);
						} else if (currString.equals("D")) { 								// box is on goal
							g2d.drawImage(boxongoal, (startX+(i * X)-(j * X))+5, startY + (i+j)*Y -(W/2), W-10, (W/2*3), this);
						}
					}
				}
			
			
			g2d.drawImage(bu,(startX+(1 * X)-(0 * X))-40, startY + (1)*Y - M, 80, 80, this);
			g2d.drawImage(bu,(startX+(3 * X)-(0 * X))-40, startY + (3)*Y - M, 80, 80, this);
			g2d.drawImage(bu,(startX+(5 * X)-(0 * X))-40, startY + (5)*Y - M, 80, 80, this);
			g2d.drawImage(bu,(startX+(7 * X)-(0 * X))-40, startY + (7)*Y - M, 80, 80, this);
			g2d.drawImage(bu,(startX+(9 * X)-(0 * X))-40, startY + (9)*Y - M, 80, 80, this);
			
			g2d.drawImage(bu,(startX-(1 * X)-(0 * X))-40, startY + (1)*Y - (M/2), 80, 80, this);
			g2d.drawImage(bu,(startX-(3 * X)-(0 * X))-40, startY + (3)*Y - (M/2), 80, 80, this);
			g2d.drawImage(bu,(startX-(5 * X)-(0 * X))-40, startY + (5)*Y - (M/2), 80, 80, this);
			g2d.drawImage(bu,(startX-(7 * X)-(0 * X))-40, startY + (7)*Y - (M/2), 80, 80, this);
			

			g2d.drawImage(go,(startX+(8 * X)-(0 * X)), startY -5 + (8)*Y, 80, 80, this);                  ///backgound size
			g2d.drawImage(go,(startX+(8 * X)-(7 * X)), startY -5 + (8+7)*Y, 80, 80, this);                  ///backgound size

			g2d.drawImage(cl, 330, 410, 160, 150, this);                  ///backgound size


			if (an == 0)an_dir = 0;
			if (an == 200)an_dir = 1;

			
			if (an_dir == 0){
				an ++;
			}else{
				an --;
			}
			
			if (bn == 0)bn_dir = 0;
			if (bn == 120)bn_dir = 1;

			
			if (bn_dir == 0){
				bn ++;
			}else{
				bn --;
			}
			
			g2d.drawImage(b, 930, 10+(an/4), 400, 300, this);                  ///backgound size
			
			g2d.drawImage(goalsquare, 200, 510-(an/3), W, W, this);                  ///backgound size
			g2d.drawImage(wall, 200, 510-(an/3)-(W/2), W, W, this);                  ///backgound size
			g2d.drawImage(go, 200, 510-(an/3)-W-5, W, W, this);                  ///backgound size

			
			g2d.drawImage(cl, 250, 110+(bn/5), 120, 110, this);                  ///backgound size




			g2d.dispose();
		}

		@Override
		public void update(Graphics g) // This method is called in response to a
										// call to repaint
		{
			if (oneFPS == null) {
				oneFPS = createImage(getWidth(), getHeight());
			}
			Graphics otherFPS = oneFPS.getGraphics();
			otherFPS.clearRect(0, 0, getWidth(), getHeight());
			paint(otherFPS);
			otherFPS.dispose();
			g.drawImage(oneFPS, 0, 0, this);
			g.dispose();
		}

		public void start() {
			thread = new Thread(this);
			thread.setName("TestImage");
			thread.setPriority(Thread.MIN_PRIORITY);
			thread.start();
		}

		public synchronized void stop() {
			thread = null;
			notify();
		}

		@Override
		public void run() {
			Thread current = Thread.currentThread();

			while (!isShowing() || getSize().width == 0) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					return;
				}
			}
			while (thread == current && isShowing()) {

				if (PRESSED == false){
					pointX = goalX;
					pointY = goalY;
					RIGHT_Ma = 0;

				}
				
				if (PRESSED == false ){
					pointX_Lu = goalX_Lu;
					pointY_Lu = goalY_Lu;
					RIGHT_Lu = 0;

				}

					if (DIR == 37){ 							// left
						LEFT_Ma = H;
						ANGLE = 0;

						if (pointX > goalX && pointY > goalY) {
							pointX = pointX - S*2;
							pointY = pointY - S;

							RIGHT_Ma = RIGHT_Ma + M;

						}
					} else if (DIR == 39) { 					// right
						LEFT_Ma = 0;
						ANGLE = 0;

						if (pointX < goalX && pointY < goalY) {
							pointX = pointX + S*2;
							pointY = pointY + S;

							RIGHT_Ma = RIGHT_Ma + M;

						}
					} else if (DIR == 38) { 					// up
						if (pointX < goalX && pointY > goalY) {
							ANGLE = 90;
							LEFT_Ma = 0;
							pointX = pointX + S*2;
							pointY = pointY - S;

							RIGHT_Ma = RIGHT_Ma + M;

						}
					} else if (DIR == 40) { 					// down
						if (pointX > goalX && pointY < goalY) {
							ANGLE = -90;
							LEFT_Ma = H;
							pointX = pointX - S*2;
							pointY = pointY + S;

							RIGHT_Ma = RIGHT_Ma + M;
						}
					}
					
					
				if (DIR_Lu == 65) 									// left   //LuiGi
					{
						LEFT_Lu = H;
						ANGLE = 0;

						if (pointX_Lu > goalX_Lu && pointY_Lu > goalY_Lu) {
							pointX_Lu = pointX_Lu - S*2;
							pointY_Lu = pointY_Lu - S;

							RIGHT_Lu = RIGHT_Lu + M;

						}
					} else if (DIR_Lu == 68) { 					// right
						LEFT_Lu = 0;
						ANGLE = 0;

						if (pointX_Lu < goalX_Lu && pointY_Lu < goalY_Lu) {
							pointX_Lu = pointX_Lu + S*2;
							pointY_Lu = pointY_Lu + S;

							RIGHT_Lu = RIGHT_Lu + M;

						}
					} else if (DIR_Lu == 87) { 					// up
						LEFT_Lu = 0;
						if (pointX_Lu < goalX_Lu && pointY_Lu > goalY_Lu) {
							pointX_Lu = pointX_Lu + S*2;
							pointY_Lu = pointY_Lu - S;

							RIGHT_Lu = RIGHT_Lu + M;

						}
					} else if (DIR_Lu == 83) { 					// down
						LEFT_Lu = H;
						if (pointX_Lu > goalX_Lu && pointY_Lu < goalY_Lu) {
							pointX_Lu = pointX_Lu - S*2;
							pointY_Lu = pointY_Lu + S;

							RIGHT_Lu = RIGHT_Lu + M;
						}
					}

					try {
						Thread.sleep(sleepTime);
						repaint();
					} catch (InterruptedException e) {
					}
			}
			thread = null;
		}
	}

	public static Image random (int a){
		
	    int i = a%6;

		
		Image w1 = null;
		Image w2 = null;
		Image w3 = null;
		Image w4 = null;
		Image w5 = null;
		Image w0 = null;
		
			try {
				w1 = ImageIO.read(new File("img/wall01.png"));

			w2 = ImageIO.read(new File("img/wall02.png"));
			w3 = ImageIO.read(new File("img/wall03.png"));
			w4 = ImageIO.read(new File("img/wall04.png"));
			w5 = ImageIO.read(new File("img/wall05.png"));
			w0 = ImageIO.read(new File("img/wall00.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (i == 0){
			return w0;
		}else if (i == 1){
			return w1;
		}else if (i == 2){
			return w2;
		}else if (i == 3){
			return w3;
		}else if (i == 4){
			return w4;
		}else if (i == 5){
			return w5;
		}else{
			return null;
		}
	}
} 
