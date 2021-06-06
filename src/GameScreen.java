import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameScreen extends JPanel implements Runnable {
	Thread thread;
	Image img;
	Graphics gr;
	Paddle pad1, pad2;
	Ball ball;
	Score score;
	
	GameScreen() {
		paddlesToMiddle();
		ballToMiddle();
		score = new Score();
		setFocusable(true);
		addKeyListener(new PaddleListener());
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
		
		if (ball.y <= 0 || ball.y >= 480)
			ball.negateYDir();
		if (ball.intersects(pad1) || ball.intersects(pad2))
			ball.negateXDir();
		
		if (ball.x <= 0) {
			score.incP2();
//			paddlesToMiddle();
			ballToMiddle();
		} else if (ball.x >= 780) {
			score.incP1();
//			paddlesToMiddle();
			ballToMiddle();
		}
	}
	public void renderAll (Graphics g) {
		score.render(g);
		pad1.render(g);
		pad2.render(g);
		ball.render(g);
	}
	public void moveAll () {
		pad1.move();
		pad2.move();
		ball.move();
	}
	//can remove if not resetting paddles after each score
	public void paddlesToMiddle () {
		pad1 = new Paddle(0, 200, 20, 100, 1);
		pad2 = new Paddle(780, 200, 20, 100, 2);
	}
	public void ballToMiddle () {
		ball = new Ball(390, (int) (Math.random() * 481), 20);
	}
	public void paint(Graphics g) {
		img = createImage(getWidth(), getHeight());
		gr = img.getGraphics();
		renderAll(gr);
		g.drawImage(img, 0, 0, this);
	}
	public void run () {
		double previousT = System.currentTimeMillis(), delta = 0;
		int millisPerTick = 20;
		while (true) {
			long currentT = System.currentTimeMillis();
			delta += (currentT - previousT) / millisPerTick;
			previousT = currentT;
			if (delta >= 1) {
				moveAll();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
	
	public class PaddleListener extends KeyAdapter {
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
