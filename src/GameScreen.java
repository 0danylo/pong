import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameScreen extends JPanel implements Runnable {
	Thread thread;
	Image image;
	Graphics graphics;
	Paddle pad1, pad2;
	Ball ball;
	Score score;
	
	GameScreen() {
		resetPaddles();
		resetBall();
		score = new Score();
		setFocusable(true);
		addKeyListener(new Listener());
		setPreferredSize(new Dimension(800, 500));
		
		thread = new Thread(this);
		thread.start();
	}
	
	public void checkCollision() {
		if (pad1.y <= 0)
			pad1.y = 0;
		if (pad1.y >= 400)
			pad1.y = 400;
		if (pad2.y <= 0)
			pad2.y = 0;
		if (pad2.y >= 400)
			pad2.y = 400;
		
		if (ball.y <= 0)
			ball.setYDir(-ball.getYDir());
		if (ball.y >= 480)
			ball.setYDir(-ball.getYDir());
		
		if (ball.intersects(pad1))
			ball.setXDir(-ball.getXDir());
		if (ball.intersects(pad2))
			ball.setXDir(-ball.getXDir());
		
		if (ball.x <= 0) {
			score.incP2();
//			resetPaddles();
			resetBall();
//			System.out.println("p2: " + score.p2);
		}
		if (ball.x >= 780) {
			score.incP1();
//			resetPaddles();
			resetBall();
//			System.out.println("p1: " + score.p1);
		}
	}
	public void drawAll (Graphics g) {
		pad1.draw(g);
		pad2.draw(g);
		ball.draw(g);
		score.draw(g);
	}
	public void moveAll () {
		pad1.move();
		pad2.move();
		ball.move();
	}
	public void resetPaddles () {
		pad1 = new Paddle(0, 200, 20, 100, 1);
		pad2 = new Paddle(780, 200, 20, 100, 2);
	}
	public void resetBall () {
		ball = new Ball(390, (int) (Math.random() * 481), 20);
	}
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		drawAll(graphics);
		g.drawImage(image, 0, 0, this);
	}
	public void run () {
		long previous = System.currentTimeMillis();
		double millis = 20, delta = 0;
		while (true) {
			long now = System.currentTimeMillis();
			delta += (now - previous) / millis;
			previous = now;
			if (delta >= 1) {
				moveAll();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
	
	public class Listener extends KeyAdapter {
		public void keyPressed (KeyEvent e) {
			pad1.keyPressed(e);
			pad2.keyPressed(e);
		}
		public void keyReleased (KeyEvent e) {
			pad1.keyReleased(e);
			pad2.keyReleased(e);
		}
	}
}
