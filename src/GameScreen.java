import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScreen extends JPanel implements KeyListener, Runnable {
	public static int player2ID;
	private Window window;
	private final Score score;
	private Ball ball;
	private PlayerPaddle p1paddle, p2paddle;
	
	GameScreen(Window w, int p2ID) {
		player2ID = p2ID;
		window = w;
		
		setPreferredSize(new Dimension(800, 500));
		setFocusable(true);
		addKeyListener(this);
		score = new Score();
		ballToMiddle();
		p1paddle = new PlayerPaddle(0, 200, 1, ball);
		p2paddle = new PlayerPaddle(780, 200, p2ID, ball);
		window.setLocationRelativeTo(null);
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void checkCollision() {
		if (ball.getY() <= 0 || ball.getY() >= 480)
			ball.negateYV();
		Rectangle in1 = ball.intersection(p1paddle), in2 = ball.intersection(p2paddle);
		if ((!in1.isEmpty() && in1.getWidth() > in1.getHeight()) || (!in2.isEmpty() && in2.getWidth() > in2.getHeight())) {
			ball.negateYV();
			ball.setXV(ball.getXV() + ball.getXV() > 0 ? 1000 : -1000);
		}	else if (!in1.isEmpty() || !in2.isEmpty())
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
	public void moveAll () {
		ball.move();
		p1paddle.move();
		p2paddle.move();
	}
	public void resetPaddles () {
		p1paddle = new PlayerPaddle((int) p1paddle.getX(), (int) p1paddle.getY(), 1, ball);
		p2paddle = new PlayerPaddle((int) p2paddle.getX(), (int) p2paddle.getY(), player2ID, ball);
	}
	public void ballToMiddle () {
		ball = new Ball(390, (int) (Math.random() * 481));
	}
	public void renderAll (Graphics g) {
		score.render(g);
		ball.render(g);
		p1paddle.render(g);
		p2paddle.render(g);
	}
	public void paint(Graphics g) {
		Image img = createImage(getWidth(), getHeight());
		Graphics imgG = img.getGraphics();
		imgG.setColor(Color.darkGray);
		imgG.setFont(new Font("Consolas", Font.ITALIC, 20));
		imgG.drawString("\"Esc\" to go back to the menu", 420, 480);
		renderAll(imgG);
		g.drawImage(img, 0, 0, this);
	}
	public void keyPressed (KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			window.dispose();
			window = new Window(false);
		}
		p1paddle.keyPressed(e);
		p2paddle.keyPressed(e);
	}
	public void keyReleased (KeyEvent e) {
		p1paddle.keyReleased(e);
		p2paddle.keyReleased(e);
	}
	public void keyTyped (KeyEvent e) { }
	public void run () {
		double previousT = System.currentTimeMillis(), currentT, delta = 0;
		int millisPerFrame = 20;
		do {
			currentT = System.currentTimeMillis();
			delta += (currentT - previousT) / millisPerFrame;
			previousT = currentT;
			if (delta >= 1) {
				moveAll();
				checkCollision();
				repaint();
				delta--;
			}
		} while (true);
	}
}
