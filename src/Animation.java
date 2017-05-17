
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Animation extends JFrame {
	public static boolean PRESSED = false;
	public static int goalX = 0;
	public static int goalY = 0;
	public static int pointX = 40;
	public static int pointY = 160;
	public static int RIGHT_GO = 0;
	public static int LEFT_GO = 0;
	public static int DIR = 0; // which key press
	public static int ANGLE = 0;
	public static int W = 40;
	public static int H = W;
	public static int S = 5;

	public static class pic extends Canvas implements Runnable {
		public BufferedImage Mario = null;
		public Image oneFPS = null;
		public Thread thread = null;
		public long sleepTime = 40;

		public pic() {
			requestFocus();
			try {
				Mario = ImageIO.read(new File("img/mario.png"));
			} catch (IOException e) {
			}
			setBackground(Color.RED);
			addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
					RIGHT_GO = 0;
					PRESSED = false;
				}

				@Override
				public void keyPressed(KeyEvent e) {
					DIR = e.getKeyCode();
					PRESSED = true;
				}
			});
		}

		@Override
		public void paint(Graphics g) // Paints this canvas.
		{
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2d.drawImage(rotateImage(Mario.getSubimage(RIGHT_GO, LEFT_GO, W, H), ANGLE, true), pointX, pointY, W, H,
					this); // print the image here
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


					if (DIR == 37) // left
					{
						LEFT_GO = W;
						ANGLE = 0;

						if (pointX > goalX) {
							pointX = pointX - S;
							RIGHT_GO = RIGHT_GO + W;
						}
					} else if (DIR == 39) { // right
						LEFT_GO = 0;
						ANGLE = 0;

						if (pointX < goalX) {
							pointX = pointX + S;
							RIGHT_GO = RIGHT_GO + W;
						}
					} else if (DIR == 38) { // up
						if (pointY > goalY) {
							ANGLE = 90;
							LEFT_GO = 0;
							pointY = pointY - S;
							RIGHT_GO = RIGHT_GO + W;
						}
					} else if (DIR == 40) { // down
						if (pointY < goalY) {
							ANGLE = -90;
							LEFT_GO = 0;
							pointY = pointY + S;
							RIGHT_GO = RIGHT_GO + W;
						}
					}

					if (RIGHT_GO > (3 * W))
						RIGHT_GO = 0;

					if ((pointY == goalY) && (pointX == goalX))
						RIGHT_GO = 0;

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
