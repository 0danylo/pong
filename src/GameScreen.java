import javax.management.ConstructorParameters;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameScreen extends JPanel implements Runnable {
	Thread thread;
	Image img;
	Graphics gr;
	Paddle p1paddle, p2paddle;
	Ball ball;
	Score score;
	int player2ID;
	
	GameScreen(int p2ID) {
		player2ID = p2ID;
		ballToMiddle();
		p1paddle = new Paddle(0, 200, 20, 100, 1, ball);
		p2paddle = new Paddle(780, 200, 20, 100, p2ID, ball);
		score = new Score();
		setFocusable(true);
		addKeyListener(new PaddleListener());
		setPreferredSize(new Dimension(800, 500));
		thread = new Thread(this);
		thread.start();
	}
	
	public void checkCollision() {
		if (ball.getY() <= 0 || ball.getY() >= 480)
			ball.negateYV();
		if (ball.intersects(p1paddle) || ball.intersects(p2paddle))
			ball.negateXV();
		
		if (p1paddle.getY() <= 0)
			p1paddle.y = 0;
		if (p1paddle.getY() >= 400)
			p1paddle.y = 400;
		if (p2paddle.getY() <= 0)
			p2paddle.y = 0;
		if (p2paddle.getY() >= 400)
			p2paddle.y = 400;
		
		if (ball.getX() <= 0) {
			score.incP2();
			ballToMiddle();
			resetPaddles();
		} else if (ball.getX() >= 780) {
			score.incP1();
			ballToMiddle();
			resetPaddles();
		}
	}
	public void renderAll (Graphics g) {
		score.render(g);
		ball.render(g);
		p1paddle.render(g);
		p2paddle.render(g);
	}
	public void moveAll () {
		ball.move();
		p1paddle.move();
		p2paddle.move();
	}
	public void resetPaddles () {
		p1paddle = new Paddle((int) p1paddle.getX(), (int) p1paddle.getY(), 20, 100, 1, ball);
		p2paddle = new Paddle((int) p2paddle.getX(), (int) p2paddle.getY(), 20, 100, player2ID, ball);
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
		double previousT = System.currentTimeMillis(), currentT, delta = 0;
		int millisPerTick = 20;
		while (true) {
			currentT = System.currentTimeMillis();
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
			p1paddle.keyPressed(e);
			p2paddle.keyPressed(e);
		}
		public void keyReleased (KeyEvent e) {
			p1paddle.keyReleased(e);
			p2paddle.keyReleased(e);
		}
	}
}
