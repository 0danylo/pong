import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScreen extends JPanel implements KeyListener, Runnable {
	public static int player2ID;
	private Window window;
	private final Scoreboard scoreboard;
	private Ball ball;
	private PlayerPaddle p1paddle, p2paddle;
	
	GameScreen(Window w, int p2ID) {
		window = w;
		player2ID = p2ID;
		
		setPreferredSize(new Dimension(800, 500));
		setFocusable(true);
		addKeyListener(this);
		scoreboard = new Scoreboard();
		ball = new Ball(390, 240);
		p1paddle = new PlayerPaddle(0, 200, 1, ball);
		p2paddle = new PlayerPaddle(790, 200, p2ID, ball);
		window.setLocationRelativeTo(null);
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void checkForCollisions() {
		if (ball.getY() <= 0 || ball.getY() >= 490)
			ball.negateYComp();
		
		Rectangle in1 = ball.intersection(p1paddle), in2 = ball.intersection(p2paddle);
		if ((!in1.isEmpty() && in1.getWidth() > in1.getHeight()) || (!in2.isEmpty() && in2.getWidth() > in2.getHeight())) {
			if (ball.getX() < 400) {
				scoreboard.incP2();
			} else {
				scoreboard.incP1();
			}
			ballToMiddleLine();
			resetPaddles();
		}	else if (!in1.isEmpty() || !in2.isEmpty()) {
			ball.negateXComp();
			ball.nudge();
		}
		
		if (p1paddle.getY() <= 0)
			p1paddle.setY(0);
		if (p1paddle.getY() >= 410)
			p1paddle.setY(410);
		if (p2paddle.getY() <= 0)
			p2paddle.setY(0);
		if (p2paddle.getY() >= 410)
			p2paddle.setY(410);
		
		if (ball.getX() < 0) {
			scoreboard.incP2();
			ballToMiddleLine();
			resetPaddles();
		} else if (ball.getX() > 790) {
			scoreboard.incP1();
			ballToMiddleLine();
			resetPaddles();
		}
	}
	public void ballToMiddleLine() {
		ball = new Ball(390, (int) (Math.random() * 481));
	}
	public void resetPaddles() {
		p1paddle = new PlayerPaddle((int) p1paddle.getX(), (int) p1paddle.getY(), 1, ball);
		p2paddle = new PlayerPaddle((int) p2paddle.getX(), (int) p2paddle.getY(), player2ID, ball);
	}
	public void moveAll() {
		ball.move();
		p1paddle.move();
		p2paddle.move();
	}
	public void renderAll(Graphics g) {
		scoreboard.render(g);
		ball.render(g);
		p1paddle.render(g);
		p2paddle.render(g);
	}
	public void paint(Graphics g) {
		Image img = createImage(getWidth(), getHeight());
		Graphics iG = img.getGraphics();
		iG.setColor(Color.darkGray);
		iG.setFont(new Font("Consolas", Font.ITALIC, 20));
		iG.drawString("\"ESC\" to go back to the menu", 415, 500);
		
		Graphics2D g2d = (Graphics2D) iG.create();
		g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{15}, 20));
		g2d.drawLine(400, 0, 400, 510);
		
		renderAll(iG);
		g.drawImage(img, 0, 0, this);
	}
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			window.dispose();
			window = new Window(false);
		}
		p1paddle.keyP(e);
		p2paddle.keyP(e);
	}
	public void keyReleased(KeyEvent e) {
		p1paddle.keyR(e);
		p2paddle.keyR(e);
	}
	public void keyTyped(KeyEvent e) { }
	public void run() {
		double previousT = System.currentTimeMillis(), currentT, diff = 0;
		int millisPerFrame = 20;
		do {
			currentT = System.currentTimeMillis();
			diff += (currentT - previousT) / millisPerFrame;
			previousT = currentT;
			if (diff >= 1) {
				moveAll();
				checkForCollisions();
				repaint();
				diff--;
			}
		} while (true);
	}
}
