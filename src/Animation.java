
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Animation extends JFrame {
	public static boolean PRESSED = false;
	public static int goalX;				//the pixel need to move to 
	public static int goalY;
	public static int pointX = 280;				//current pixel
	public static int pointY = 100	;
	public static int RIGHT_GO = 0;				//x-position of sub-image 
	public static int LEFT_GO = 0;				//y-position of sub-image 
	public static int DIR = 0; 					// moving direction
	public static int ANGLE = 0;				//rotate or not
	public static int W = 80;					//sub-picture dimension
	public static int M = 60;					//sub-picture dimension
	public static int H = 75;
	public static int X = (W/2);					//sub-picture dimension
	public static int Y = (W/4);					//sub-picture dimension

	public static int S = 3;					//speed
	public static long sleepTime = 20;			//delay
	

	public static class pic extends Canvas implements Runnable {
		public BufferedImage Mario = null;
		public BufferedImage Box = null;
		public BufferedImage goalsquare = null;
		public BufferedImage empty = null;
		public BufferedImage player = null;
		public BufferedImage wall = null;
		public BufferedImage boxongoal = null;
		
		public Image oneFPS = null;
		public Thread thread = null;
		
		ArrayList<ArrayList<String>> map;
		private Game game;
		
		public pic(ArrayList<ArrayList<String>> map, Game game) {
			setBackground(Color.black);
			this.map = map;
			this.game = game;
			try {
				Mario = ImageIO.read(new File("img/mario.png"));
				Box = ImageIO.read(new File("img/box.png"));
				goalsquare = ImageIO.read(new File("img/goal.png"));
				empty = ImageIO.read(new File("img/ground.png"));
				player = ImageIO.read(new File("img/player.png"));
				wall = ImageIO.read(new File("img/wall.png"));
				boxongoal = ImageIO.read(new File("img/goal-box.png"));
				
			} catch (IOException e) {
			}
			requestFocus();
		}

		@Override
		public void paint(Graphics g) 																		// Paints this canvas.
		{			
			Graphics2D g2d = (Graphics2D) g;

			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);


			if (WarehouseBossInterface.state == WarehouseBossInterface.STATE.GAME) {
				ArrayList<ArrayList<String>> currMap = game.getLevel(WarehouseBossInterface.currLevel).getMap();
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						String currString = currMap.get(j).get(i);
						if (currString.equals("B")) { 										// Box
							g2d.drawImage(Box, (400+(i * X)-(j * X)), (i+j)*Y, W, W, this);
						} else if (currString.equals("T")) { 								// goal square
							g2d.drawImage(goalsquare, (400+(i * X)-(j * X)), (i+j)*Y, W, W, this);
						} else if (currString.equals("E")) { 								// empty
							g2d.drawImage(empty, (400+(i * X)-(j * X)), (i+j)*Y, W, W, this);
						} else if (currString.equals("P")||currString.equals("Q")) {
							g2d.drawImage(empty, (400+(i * X)-(j * X)), (i+j)*Y, W, W, this);
							goalX = (400+(i * X)-(j * X));
							goalY =(i+j)*Y;			
							g2d.drawImage(rotateImage(Mario.getSubimage(RIGHT_GO, LEFT_GO, M, H), 0, true), pointX, (pointY-(H-M)), M, H, this);
						} else if (currString.equals("O")||currString.equals("R")) {
							g2d.drawImage(goalsquare, (400+(i * X)-(j * X)), (i+j)*Y, W, W, this);
							goalX = (400+(i * X)-(j * X));
							goalY =(i+j)*Y;			
							g2d.drawImage(rotateImage(Mario.getSubimage(RIGHT_GO, LEFT_GO, M, H), 0, true), pointX, (pointY-(H-M)), M, H, this);

						} else if (currString.equals("W")) { 								// wall
							g2d.drawImage(wall, (400+(i * X)-(j * X)), (i+j)*Y, W, W, this);
						} else if (currString.equals("D")) { 								// box is on goal
							g2d.drawImage(boxongoal, (400+(i * X)-(j * X)), (i+j)*Y, W, W, this);
						}
					}
				}
			} else if (WarehouseBossInterface.state == WarehouseBossInterface.STATE.MENU) {
			}
			

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
					Thread.sleep(500);
				} catch (InterruptedException e) {
					return;
				}
			}
			while (thread == current && isShowing()) {

				if (PRESSED == false && (!(java.lang.Math.abs(2*(goalY - pointY)) == java.lang.Math.abs(goalX - pointX)))){
					pointX = goalX;
					pointY = goalY;

				}

					if (DIR == 37) // left
					{
						LEFT_GO = H;
						ANGLE = 0;

						if (pointX > goalX && pointY > goalY) {
							pointX = pointX - S*2;
							pointY = pointY - S;

							RIGHT_GO = RIGHT_GO + M;

						}
					} else if (DIR == 39) { // right
						LEFT_GO = 0;
						ANGLE = 0;

						if (pointX < goalX && pointY < goalY) {
							pointX = pointX + S*2;
							pointY = pointY + S;

							RIGHT_GO = RIGHT_GO + M;

						}
					} else if (DIR == 38) { // up&&
						if (pointX < goalX && pointY > goalY) {
							ANGLE = 90;
							LEFT_GO = 0;
							pointX = pointX + S*2;
							pointY = pointY - S;

							RIGHT_GO = RIGHT_GO + M;

						}
					} else if (DIR == 40) { // down
						if (pointX > goalX && pointY < goalY) {
							ANGLE = -90;
							LEFT_GO = H;
							pointX = pointX - S*2;
							pointY = pointY + S;

							RIGHT_GO = RIGHT_GO + M;

						}
					}


						
						if (RIGHT_GO > (3 * M))
							RIGHT_GO = 0;

//					if ((pointY == goalY) && (pointX == goalX))
//						RIGHT_GO = 0;

					try {
						Thread.sleep(sleepTime);
						repaint();
					} catch (InterruptedException e) {
					}
				
			}
			thread = null;
		}
	}

	public static BufferedImage rotateImage(final BufferedImage image, final int angdeg, final boolean d) {
		int w = image.getWidth();
		int h = image.getHeight();
		int type = image.getColorModel().getTransparency();
		BufferedImage img;
		Graphics2D graphics2d;
		(graphics2d = (img = new BufferedImage(w, h, type)).createGraphics())
				.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2d.rotate(d ? -Math.toRadians(angdeg) : Math.toRadians(angdeg), w / 2, h / 2);
		graphics2d.drawImage(image, 0, 0, null);
		graphics2d.dispose();
		return img;
	}

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				final Animation runer = new Animation();
//
//				runer.canvas.start();
//			}
//		});
//	}
} 
